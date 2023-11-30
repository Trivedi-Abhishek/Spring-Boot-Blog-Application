package com.abhishek.blogapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abhishek.blogapi.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment,Integer>{
    
}
