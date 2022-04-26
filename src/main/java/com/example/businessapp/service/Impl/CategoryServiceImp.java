package com.example.businessapp.service.Impl;

import com.example.businessapp.dto.CategoryDto;
import com.example.businessapp.entity.Category;
import com.example.businessapp.repository.CategoryRepository;
import com.example.businessapp.service.CategoryService;
import com.example.businessapp.utils.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImp implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<CategoryDto> getAllCategories(){
        return ObjectMapperUtils.mapAll(categoryRepository.findAll(), CategoryDto.class);
    }

    @Override
    public CategoryDto getCategory(String name) {
        return null;
    }

    @Override
    public void saveCategory(CategoryDto categoryDto) {
        categoryRepository.save(ObjectMapperUtils.map(categoryDto, Category.class));
    }

    @Override
    public void updateCategory(CategoryDto categoryDto) {

    }

    @Override
    public void deleteCategoryById(Long id) {

    }

    @Override
    public void deleteCategory(Long id) {

    }

}
