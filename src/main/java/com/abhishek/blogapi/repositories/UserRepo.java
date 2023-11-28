package com.abhishek.blogapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abhishek.blogapi.entities.User;

public interface UserRepo extends JpaRepository<User,Integer> {
    
}
