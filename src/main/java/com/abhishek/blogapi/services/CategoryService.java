package com.abhishek.blogapi.services;

import java.util.List;

import com.abhishek.blogapi.payloads.CategoryDto;

public interface CategoryService {
    
    public CategoryDto createCategory(CategoryDto categoryDto);
    public CategoryDto updateCategory(CategoryDto categoryDto,Integer id);
    public void deleteCategory(Integer id);
    public CategoryDto getCategoryById(Integer id);
    public List<CategoryDto> getCategories();
}
