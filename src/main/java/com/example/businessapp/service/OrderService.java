package com.example.businessapp.service;


import com.example.businessapp.dto.OrdersDto;
import com.example.businessapp.entity.Orders;
import com.example.businessapp.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public interface OrderService {

    List<OrdersDto> getOrders();

    OrdersDto createOrder(OrdersDto ordersDto);

    OrdersDto update(OrdersDto ordersDto);

    void deleteOrder(Long id);

    void deleteOrderByAdmin(Long id);

}
