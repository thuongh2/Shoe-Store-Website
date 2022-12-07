package com.example.businessapp.repository;

import com.example.businessapp.entity.Orders;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderRepository extends MongoRepository<Orders, String> {
    List<Orders> findByUserId(String userId);
}
