package com.abhishek.blogapi.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import com.abhishek.blogapi.entities.Comment;

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
    private Set<CommentDto> comments=new HashSet<>();
}
