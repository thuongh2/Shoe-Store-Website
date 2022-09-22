package com.example.businessapp.controller;


import com.example.businessapp.dto.CategoryDto;
import com.example.businessapp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "https://shoes-store-udtt.vercel.app/")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/s/categories")
    public List<CategoryDto> getAllCategory(){
        return categoryService.getAllCategories();
    }

    @PostMapping("/s/category")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveCategory(@Valid @ModelAttribute CategoryDto categoryDto){
        categoryService.saveCategory(categoryDto);
    }
}
