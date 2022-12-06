package com.example.businessapp.repository;

import com.example.businessapp.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderItem, Long> {

    List<OrderItem> findOrderDetailByActiveIsTrue();

    List<OrderItem> findOrderDetailByActiveIsTrueAndCheckIsTrue();

    List<OrderItem> findOrderDetailByActiveIsTrueAndCheckIsFalse();

    OrderItem findOrderDetailById(Long id);

    List<OrderItem> findOrderDetailByUser_Username(String username);

    List<OrderItem> findOrderDetailByProduct_Id(Long id);

    List<OrderItem> findOrderDetailByOrders_CustomerEmail(String email);



}
