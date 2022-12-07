package com.example.businessapp.repository;

import com.example.businessapp.entity.OrderItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderItemRepository extends MongoRepository<OrderItem, String> {
    List<OrderItem> findByOrderId(String orderId);
}
