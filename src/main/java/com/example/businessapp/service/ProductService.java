package com.example.businessapp.service;

import com.example.businessapp.dto.ProductDto;
import com.example.businessapp.dto.ProductRequest;

import java.util.List;

public interface ProductService {

        List<ProductDto> getProducts();

        ProductDto getProduct(Long id);

        List<ProductDto> getProductsByAdmin();

        ProductDto saveProduct (ProductRequest productRequest) throws IllegalArgumentException;

        ProductDto updateProduct (ProductRequest productRequest) throws Exception;

        void deleteProduct(Long id);

        void deleteProductByAdmin(Long id);

        List<ProductDto> getProductByCategory(Long id);
}
