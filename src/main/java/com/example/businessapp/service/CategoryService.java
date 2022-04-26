package com.example.businessapp.service;

import com.example.businessapp.dto.CategoryDto;
import java.util.List;

public interface CategoryService {

    List<CategoryDto> getAllCategories();

    CategoryDto getCategory(String name);

    void saveCategory(CategoryDto categoryDto);

    void updateCategory(CategoryDto categoryDto);

    //soft delete
    void deleteCategoryById(Long id);

    void deleteCategory(Long id);
}
