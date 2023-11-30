package com.abhishek.blogapi.services;

import com.abhishek.blogapi.payloads.CommentDto;

public interface CommentService {
    
    CommentDto createComment(CommentDto commentDto,Integer postId);
    void deleteComment(Integer commentId);
}
