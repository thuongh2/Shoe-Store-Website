package com.example.businessapp.service.Impl;

import com.example.businessapp.dto.ProductDto;
import com.example.businessapp.dto.ProductRequest;
import com.example.businessapp.entity.Category;
import com.example.businessapp.entity.Image;
import com.example.businessapp.entity.Product;
import com.example.businessapp.entity.Size;
import com.example.businessapp.exception.CategoryNotFoundException;
import com.example.businessapp.exception.ProductNotFoundException;
import com.example.businessapp.repository.CategoryRepository;
import com.example.businessapp.repository.ProductRepository;
import com.example.businessapp.service.ProductService;

import com.example.businessapp.utils.ObjectMapperUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Id;
import java.util.List;
import java.util.function.Predicate;

@Service
@Slf4j
public class ProductServiceImp implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CloudinaryServiceImp cloudinaryService;

    public Product removeItem(Product product){
        Predicate<Size> isQualified = size -> !size.isActive();
        product.getSizes().removeIf(isQualified);

        Predicate<Image> isImageQualified = image -> !image.isActive();
        product.getImage().removeIf(isImageQualified);

        return product;
    }


    // get all product by user
    @Override
    public List<ProductDto> getProducts() {
        List<Product> products = productRepository.findProductByActiveIsTrue();

        // remove filed not active
        for (Product p: products) {
            p = removeItem(p);
        }

        return ObjectMapperUtils.mapAll(products, ProductDto.class);
    }


    // get all product by admin and super-admin
    @Override
    public List<ProductDto> getProductsByAdmin(){
        return ObjectMapperUtils.mapAll(productRepository.findAll(), ProductDto.class);
    }

    @Override
    public ProductDto saveProduct(ProductRequest productRequest) throws IllegalArgumentException {
        // find category in db
        Category category = categoryRepository.findById(productRequest.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException(String.format("Category with id %d not found", productRequest.getCategoryId())));

        // convert productRequest to product
        Product product = ObjectMapperUtils.map(productRequest, Product.class);

        product.setCategory(category);
        // save and return product
        return ObjectMapperUtils.map(productRepository.save(product), ProductDto.class);

    }

    @Override
    public ProductDto updateProduct(ProductRequest productRequest) throws Exception {
        // find category in db
        Category category = categoryRepository.findById(productRequest.getCategoryId())
                .orElseThrow(() -> {
                    return new CategoryNotFoundException(String.format("Category with id %d not found", productRequest.getCategoryId()));
                });


        // convert productRequest to product
        Product product = productRepository.findById(productRequest.getId())
                .orElseThrow(()-> new ProductNotFoundException(String.format("Category with id %d not found", productRequest.getId())));

        log.info("Update product id: " + product.getId());
        log.info("Update category id: " +category.getId());

        //update product
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setCategory(category);

        // save and return product
        return ObjectMapperUtils.map(productRepository.save(product), ProductDto.class);
    }

    @Override
    public void deleteProduct(Long id) throws IllegalArgumentException {
        Product product = productRepository.findById(id)
                .orElseThrow(()-> new ProductNotFoundException(String.format("Category with id %d not found", id)));

        log.info("Delete product: " + product.getName());

        product.setActive(!product.isActive());

        productRepository.save(product);

    }

    @Override
    public void deleteProductByAdmin(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(()-> new ProductNotFoundException(String.format("Category with id %d not found", id)));
        productRepository.delete(product);
    }

    @Override
    public List<ProductDto> getProductByCategory(Long id) {
        //find category of list product
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(String.format("Category with id %d not found", id)));
        log.info("Category Name: " + category.getName());
        return ObjectMapperUtils.mapAll(productRepository.findProductByCategoryAndActiveIsTrue(category), ProductDto.class);
    }

    //get detail product by id
    @Override
    public ProductDto getProduct(Long id){
        log.info("product id: "+ id);

        Product product = productRepository.findById(id)
                .orElseThrow(()-> new ProductNotFoundException(String.format("Category with id %d not found", id)));

        product = removeItem(product);

        log.info(product.getName());

        return ObjectMapperUtils.map(product, ProductDto.class);
    }

}
