package com.example.businessapp.manager;

import com.example.businessapp.entity.Product;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductManager extends BaseService{

    public List<Product> findProductByNameLike(String name){
        Query query = new Query();
        query.addCriteria(Criteria.where("tagName").regex(name));
        return mongoTemplate.find(query, Product.class);
    }
}
