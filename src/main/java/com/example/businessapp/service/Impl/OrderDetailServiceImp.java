package com.example.businessapp.service.Impl;

import com.example.businessapp.dto.OrderDetailDto;
import com.example.businessapp.dto.OrderDetailRequest;
import com.example.businessapp.entity.*;
import com.example.businessapp.exception.NotFoundException;
import com.example.businessapp.exception.ProductNotFoundException;
import com.example.businessapp.repository.OrderDetailRepository;
import com.example.businessapp.repository.OrderRepository;
import com.example.businessapp.repository.ProductRepository;
import com.example.businessapp.repository.UserRepository;
import com.example.businessapp.service.OrderDetailService;
import com.example.businessapp.utils.ObjectMapperUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
public class OrderDetailServiceImp implements OrderDetailService {
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<OrderDetailDto> getOrderDetails() {
        return ObjectMapperUtils.mapAll(orderDetailRepository.findOrderDetailByActiveIsTrue(), OrderDetailDto.class);
    }

    @Override
    public List<OrderDetailDto> getOrderDetailsByProduct(Long productId) {
        return ObjectMapperUtils.mapAll(orderDetailRepository.findOrderDetailByProduct_Id(productId),
                OrderDetailDto.class);
    }

    @Override
    public List<OrderDetailDto> getOrderDetailsByUser(String username) {
        return ObjectMapperUtils.mapAll(orderDetailRepository.findOrderDetailByUser_Username(username),
                OrderDetailDto.class);
    }

    @Override
    public List<OrderDetailDto> getOrderDetailsByEmail(String email) {
        return ObjectMapperUtils.mapAll(orderDetailRepository.findOrderDetailByOrders_CustomerEmail(email),
                OrderDetailDto.class);
    }

    @Override
    public OrderDetailDto createOrderDetail(OrderDetailRequest orderDetailRequest) throws IOException {

        Orders orders = orderRepository.findById(orderDetailRequest.getOrderId())
                .orElseThrow(()-> new NotFoundException("orders"+ orderDetailRequest.getOrderId() +" not found"));

        Product product = productRepository.findById(orderDetailRequest.getProductId())
                .orElseThrow(()-> new ProductNotFoundException(String.format("Category with id %d not found", orderDetailRequest.getProductId())));

        User user = userRepository.findByUsername("thuong");

        // check quantity of size
        for(Size size: product.getSizes()){
            if(size.getSize() == orderDetailRequest.getSize()){
                if(size.getQuantity() < orderDetailRequest.getQuantity()){
                    throw new IOException("San pham vuot qua");
                }
                size.setQuantity(size.getQuantity() - orderDetailRequest.getQuantity());
                log.info(String.format(" Size sau khi orders: %d",size.getQuantity()));
            }
        }

        OrderDetail orderDetail = new OrderDetail();

        // set attribute of order details
        orderDetail.setId(orderDetailRequest.getId());
        orderDetail.setOrders(orders);
        orderDetail.setProduct(product);
        orderDetail.setUser(user);
        orderDetail.setQuantity(orderDetailRequest.getQuantity());
        orderDetail.setPrice(product.getPrice());
        orderDetail.setAmount(product.getPrice() * orderDetail.getQuantity());
        orderDetail.setCheck(false);

        //set amount of orders in formation
        orders.setAmount(orders.getAmount() + orderDetail.getAmount());

        return ObjectMapperUtils.map(orderDetailRepository.save(orderDetail),
                OrderDetailDto.class);
    }

    @Override
    public OrderDetailDto updateOrderDetail(OrderDetailRequest orderDetailRequest) {
        // update quantity
        //OrderDetail orderDetail = orderDetailRepository.findOrderDetailById(orderDetailRequest.getOrderId());





        return null;
    }

    //soft delete
    @Override
    public void deleteOrderDetail(Long id) {
        OrderDetail orderDetail = orderDetailRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Order Details not found: "+ id));

        orderDetail.setActive(!orderDetail.isActive());

        orderDetailRepository.save(orderDetail);
    }

    @Override
    public void deleteOrderDetailByUser(Long id) {
        log.warn("Delete order details by admin" + id);

        OrderDetail orderDetail = orderDetailRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Order Details not found: "+ id));

        orderDetailRepository.delete(orderDetail);
    }

    @Override
    public void checkOrderDetail(Long id) {
        OrderDetail orderDetail = orderDetailRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Order Details not found: "+ id));

        orderDetail.setCheck(!orderDetail.isCheck());

        orderDetailRepository.save(orderDetail);
    }
}
