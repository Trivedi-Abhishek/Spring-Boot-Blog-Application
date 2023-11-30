package com.abhishek.blogapi.blog.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.abhishek.blogapi.entities.User;
import com.abhishek.blogapi.exceptions.ResourceNotFoundException;
import com.abhishek.blogapi.repositories.UserRepo;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    public UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        User user=userRepo.findByEmail(username).orElseThrow(()->new ResourceNotFoundException("User","Email"+ username, 0));

        return user;
    }
    
}
