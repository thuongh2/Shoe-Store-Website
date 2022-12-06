package com.example.businessapp.repository;

import com.example.businessapp.entity.OrderItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderItemRepository extends MongoRepository<OrderItem, String> {

}
