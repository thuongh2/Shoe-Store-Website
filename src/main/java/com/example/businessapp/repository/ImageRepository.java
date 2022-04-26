package com.example.businessapp.repository;

import com.example.businessapp.entity.Image;
import com.example.businessapp.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {

    List<Image> findByProductAndActiveIsTrue(Product product);
}