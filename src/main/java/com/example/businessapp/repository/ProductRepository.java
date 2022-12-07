package com.example.businessapp.repository;

import com.example.businessapp.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    List<Product> findByName(String name);

    List<Product> findProductByCategoryId(String category);
}
