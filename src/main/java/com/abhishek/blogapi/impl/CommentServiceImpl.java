package com.abhishek.blogapi.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abhishek.blogapi.entities.Comment;
import com.abhishek.blogapi.entities.Post;
import com.abhishek.blogapi.exceptions.ResourceNotFoundException;
import com.abhishek.blogapi.payloads.CommentDto;
import com.abhishek.blogapi.repositories.CommentRepo;
import com.abhishek.blogapi.repositories.PostRepo;
import com.abhishek.blogapi.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {
        Post post=postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "Post id", postId));
        
        Comment comment=modelMapper.map(commentDto,Comment.class);
        
        comment.setPost(post);
        Comment savedComment=commentRepo.save(comment);
        return modelMapper.map(savedComment,CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment comment=commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment", "Comment Id", commentId));
        commentRepo.delete(comment);
    }
    
}
