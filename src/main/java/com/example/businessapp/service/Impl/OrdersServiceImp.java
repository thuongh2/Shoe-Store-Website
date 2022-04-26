package com.example.businessapp.service.Impl;

import com.example.businessapp.dto.OrdersDto;
import com.example.businessapp.entity.Orders;
import com.example.businessapp.exception.NotFoundException;
import com.example.businessapp.repository.OrderRepository;
import com.example.businessapp.service.OrderService;
import com.example.businessapp.utils.ObjectMapperUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class OrdersServiceImp implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    //get all order by admin
    @Override
    public List<OrdersDto> getOrders() {
        return ObjectMapperUtils.mapAll(orderRepository.findOrdersByActiveIsTrue(), OrdersDto.class);
    }


    @Override
    public OrdersDto createOrder(OrdersDto ordersDto) {

        Orders orders = ObjectMapperUtils.map(ordersDto, Orders.class);

        return ObjectMapperUtils.map(orderRepository.save(orders), OrdersDto.class);
    }

    @Override
    public OrdersDto update(OrdersDto ordersDto) {
        Orders orders = orderRepository.findById(ordersDto.getId())
                .orElseThrow(()-> new NotFoundException("orders"+ ordersDto.getId() +" not found"));

        log.info(orders.getCustomerAddress());

        orders.setCustomerName(ordersDto.getCustomerName());
        orders.setCustomerAddress(ordersDto.getCustomerAddress());
        orders.setCustomerEmail(ordersDto.getCustomerEmail());
        orders.setCustomerPhone(ordersDto.getCustomerPhone());

        return ObjectMapperUtils.map(orders, OrdersDto.class);
    }

    @Override
    public void deleteOrder(Long id) {
        log.info("Find Order Id " + id);
        Orders orders = orderRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("orders"+ id +" not found"));

        orders.setActive(!orders.isActive());
        log.info("After delete: is active "+ orders.isActive());
        orderRepository.save(orders);
    }

    @Override
    public void deleteOrderByAdmin(Long id) {

        log.warn("Find Order Id " + id);
        Orders orders = orderRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("orders"+ id +" not found"));

        orderRepository.delete(orders);
    }
}
