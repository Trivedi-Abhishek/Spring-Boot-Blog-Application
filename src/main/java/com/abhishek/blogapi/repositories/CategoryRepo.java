package com.abhishek.blogapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abhishek.blogapi.entities.Category;

public interface CategoryRepo extends JpaRepository<Category,Integer>{
    
}
