package com.abhishek.blogapi.services;

import java.util.List;

import com.abhishek.blogapi.payloads.UserDto;

public interface UserService {
    
    UserDto createUser(UserDto user);
    UserDto updateUser(UserDto user,Integer id);
    UserDto getUserById(Integer id);
    List<UserDto> getAllUsers();
    void deleteUser(Integer id);

}
