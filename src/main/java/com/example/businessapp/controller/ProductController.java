package com.example.businessapp.controller;

import com.example.businessapp.dto.ProductDto;
import com.example.businessapp.dto.ProductRequest;
import com.example.businessapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public List<ProductDto> getAllProduct(){
        return productService.getProducts();
    }

    @GetMapping("/s/products")
    public List<ProductDto> getAllProductByAdmin(){
        return productService.getProductsByAdmin();
    }

    @GetMapping("/product/{id}")
    public ProductDto getProductById(@PathVariable(name="id") Long id){
        return productService.getProduct(id);
    }

    @GetMapping("/product")
    public List<ProductDto> getProductByCategory(@RequestParam(value="category") Long id){
        return productService.getProductByCategory(id);
    }


    @PostMapping("/s/product/save")
    public ProductDto saveProduct(@Valid @ModelAttribute ProductRequest product){
        return productService.saveProduct(product);

    }

    @PostMapping("/s/product/update")
    public ProductDto update(@Valid @ModelAttribute ProductRequest product) throws Exception{
        return productService.updateProduct(product);

    }


    @GetMapping("/s/product/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProduct(@PathVariable(name="id") Long id) {
        productService.deleteProduct(id);
    }

    @GetMapping("/s/product/delete-admin/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProductByAdmin(@PathVariable(name="id") Long id) {
        productService.deleteProductByAdmin(id);
    }
}
