package com.example.businessapp.repository;

import com.example.businessapp.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

    List<OrderDetail> findOrderDetailByActiveIsTrue();

    List<OrderDetail> findOrderDetailByActiveIsTrueAndCheckIsTrue();

    List<OrderDetail> findOrderDetailByActiveIsTrueAndCheckIsFalse();

    OrderDetail findOrderDetailById(Long id);

    List<OrderDetail> findOrderDetailByUser_Username(String username);

    List<OrderDetail> findOrderDetailByProduct_Id(Long id);

    List<OrderDetail> findOrderDetailByOrders_CustomerEmail(String email);



}
