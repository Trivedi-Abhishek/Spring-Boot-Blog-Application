package com.abhishek.blogapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abhishek.blogapi.entities.Category;
import com.abhishek.blogapi.entities.Post;
import com.abhishek.blogapi.entities.User;


public interface PostRepo extends JpaRepository<Post, Integer> {
    
    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);

    List<Post> findByTitleContaining(String title);
}
