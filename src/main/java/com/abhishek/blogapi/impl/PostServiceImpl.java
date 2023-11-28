package com.abhishek.blogapi.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.abhishek.blogapi.entities.Category;
import com.abhishek.blogapi.entities.Post;
import com.abhishek.blogapi.entities.User;
import com.abhishek.blogapi.exceptions.ResourceNotFoundException;
import com.abhishek.blogapi.payloads.PostDto;
import com.abhishek.blogapi.payloads.PostResponse;
import com.abhishek.blogapi.repositories.CategoryRepo;
import com.abhishek.blogapi.repositories.PostRepo;
import com.abhishek.blogapi.repositories.UserRepo;
import com.abhishek.blogapi.services.PostService;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;
    @Override
    public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) {
        
        Post post=modelMapper.map(postDto,Post.class);
        User user=userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "User Id", userId));
        Category category=categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "Category Id", categoryId));
        
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);
        Post savedPost=postRepo.save(post);
        return modelMapper.map(savedPost,PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post=postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "Post Id", postId));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());
        post.setAddedDate(postDto.getAddedDate());
        Post savedPost=postRepo.save(post);
        return modelMapper.map(savedPost,PostDto.class);
    }

    @Override
    public void deletePost(Integer postId) {
        Post post=postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "Post Id", postId));
        postRepo.delete(post);
    }

    @Override
    public PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir) {
        
        Sort sort=sortDir.equalsIgnoreCase("asc")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable p=PageRequest.of(pageNumber, pageSize, sort);
        Page<Post> pagePost=postRepo.findAll(p);
        List<Post> posts=pagePost.getContent();
        List<PostDto> postDtos=posts.stream().map((post)->modelMapper.map(post,PostDto.class)).collect(Collectors.toList());

        PostResponse response=new PostResponse();
        response.setContent(postDtos);
        response.setPageNumber(pagePost.getNumber());
        response.setPageSize(pagePost.getSize());
        response.setTotalElements(pagePost.getTotalElements());
        response.setTotalPages(pagePost.getTotalPages());
        response.setLastPage(pagePost.isLast());
        return response;
    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post post=postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "Post id", postId));
        return modelMapper.map(post,PostDto.class);
        
    }

    @Override
    public List<PostDto> getPostsByCategory(Integer categoryId) {
        Category cat=categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "Category Id", categoryId));
        List<Post> posts=postRepo.findByCategory(cat);
        List<PostDto> postDtos=posts.stream().map((post)->modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> getPostsByUser(Integer userId) {
        
        User user=userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "User Id", userId));
        List<Post> posts=postRepo.findByUser(user);
        List<PostDto> postDtos=posts.stream().map((post)->modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {
        List<Post> posts=postRepo.findByTitleContaining(keyword);
        List<PostDto> postDtos=posts.stream().map((post)->modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }
    
}
