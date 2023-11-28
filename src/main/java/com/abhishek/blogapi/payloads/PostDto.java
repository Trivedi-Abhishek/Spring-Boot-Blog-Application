package com.abhishek.blogapi.payloads;

import java.util.Date;

import com.abhishek.blogapi.entities.Category;
import com.abhishek.blogapi.entities.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PostDto {
    
    private Integer postId;
    private String title;
    private String content;
    private String imageName;
    private Date addedDate;
    private UserDto user;
    private CategoryDto category;
}
