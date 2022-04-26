package com.example.businessapp.repository;

import com.example.businessapp.entity.Category;
import com.example.businessapp.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

//    @Query(value = "select * from products , (select product_id from size where size.active = true) as s\n" +
//            "where products.active = true and s.product_id = products.id;", nativeQuery = true)
    List<Product> findProductByActiveIsTrue();

    List<Product> findProductByCategoryAndActiveIsTrue(Category category);

//    @Query(value = "select * from products , (select product_id from size where size.active = true) as s\n" +
//            "where products.active = true and s.product_id = products.id", nativeQuery = true)
    List<Product> findProduct();

}
