package com.abhishek.blogapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhishek.blogapi.payloads.ApiResponse;
import com.abhishek.blogapi.payloads.CategoryDto;
import com.abhishek.blogapi.services.CategoryService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) {
        
        CategoryDto catDto=categoryService.createCategory(categoryDto);

        return new ResponseEntity<CategoryDto>(catDto,HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto,@PathVariable Integer id) {
        
        CategoryDto catDto=categoryService.updateCategory(categoryDto,id);

        return new ResponseEntity<CategoryDto>(catDto,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer id) {
        
        // CategoryDto cat=categoryService.getCategoryById(id);
        categoryService.deleteCategory(id);

        return new ResponseEntity<ApiResponse>(new ApiResponse("Category with given id deleted successfully",true),HttpStatus.OK);
    }
    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getAllCategories(){
        List<CategoryDto> categoryDtos=categoryService.getCategories();
        return new ResponseEntity<List<CategoryDto>>(categoryDtos,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getAllCategories(@PathVariable Integer id){
        CategoryDto categoryDto=categoryService.getCategoryById(id);
        return new ResponseEntity<CategoryDto>(categoryDto,HttpStatus.OK);
    }
}
