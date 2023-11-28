package com.abhishek.blogapi.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abhishek.blogapi.entities.Category;
import com.abhishek.blogapi.entities.User;
import com.abhishek.blogapi.exceptions.ResourceNotFoundException;
import com.abhishek.blogapi.payloads.CategoryDto;
import com.abhishek.blogapi.payloads.UserDto;
import com.abhishek.blogapi.repositories.CategoryRepo;
import com.abhishek.blogapi.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category cat=modelMapper.map(categoryDto, Category.class);
        Category savedCat=categoryRepo.save(cat);
        return modelMapper.map(savedCat,CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer id) {
        Category cat=categoryRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Category", "Category Id", id));
        cat.setCategoryDescription(categoryDto.getCategoryDescription());
        cat.setCategoryTitle(categoryDto.getCategoryTitle());
        categoryRepo.save(cat);
        return modelMapper.map(cat,CategoryDto.class);
    }

    @Override
    public void deleteCategory(Integer id) {
        Category cat=categoryRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Category", "Category Id", id));
        categoryRepo.delete(cat);
    }

    @Override
    public CategoryDto getCategoryById(Integer id) {
        Category cat=categoryRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Category", "Category Id", id));
        return modelMapper.map(cat, CategoryDto.class);

    }

    @Override
    public List<CategoryDto> getCategories() {
        List<Category> categories=categoryRepo.findAll();
        List<CategoryDto> categoryDtos=categories.stream().map(category->modelMapper.map(category,CategoryDto.class)).collect(Collectors.toList());
        return categoryDtos;
    }
    
    
}
