package com.example.businessapp.service;

import com.example.businessapp.dto.OrderDetailDto;
import com.example.businessapp.dto.OrderDetailRequest;
import com.example.businessapp.entity.OrderDetail;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface OrderDetailService {

    List<OrderDetailDto> getOrderDetails();

    List<OrderDetailDto> getOrderDetailsByProduct(Long productId);

    List<OrderDetailDto> getOrderDetailsByUser(String username);

    List<OrderDetailDto> getOrderDetailsByEmail(String email);

    /* create order (check price, customer...)
     re quest orders, product, quantity
     auto get orders by id, product by id, user in spring security
     quantity , price = product.price, amount = price * quantity
     order.amount += amount, product.quantity -= quantity
     check is true
     ? size */
    OrderDetailDto createOrderDetail(OrderDetailRequest orderDetailDto) throws IOException;

    OrderDetailDto updateOrderDetail(OrderDetailRequest orderDetailDto);

    void deleteOrderDetail(Long id);

    void deleteOrderDetailByUser(Long id);

    void checkOrderDetail(Long id);

}
