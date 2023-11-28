package com.abhishek.blogapi.impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abhishek.blogapi.entities.User;
import com.abhishek.blogapi.exceptions.ResourceNotFoundException;
import com.abhishek.blogapi.payloads.UserDto;
import com.abhishek.blogapi.repositories.UserRepo;
import com.abhishek.blogapi.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user=this.dtoToUser(userDto);
        User savedUser=this.userRepo.save(user);
        return this.userToDto(savedUser);

        // throw new UnsupportedOperationException("Unimplemented method 'createUser'");
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer id) {
        User user=this.userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("User","id",id));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        User updatedUser=this.userRepo.save(user);
        return this.userToDto(updatedUser);
    }

    @Override
    public UserDto getUserById(Integer id) {
        User user=userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("User","id",id));

        return this.userToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users=userRepo.findAll();
        List<UserDto> userDtos=users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public void deleteUser(Integer id) {
        User user=userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("User", "id", id));
        userRepo.delete(user);
    }
    
    private User dtoToUser(UserDto userDto){
        User user = modelMapper.map(userDto,User.class);

        // user.setId(userDto.getId());
        // user.setName(userDto.getName());
        // user.setAbout(userDto.getAbout());
        // user.setEmail(userDto.getEmail());
        // user.setPassword(userDto.getPassword());
        return user;
    }
    private UserDto userToDto(User user){
        UserDto userDto = modelMapper.map(user, UserDto.class);
        // userDto.setId(user.getId());
        // userDto.setName(user.getName());
        // userDto.setAbout(user.getAbout());
        // userDto.setEmail(user.getEmail());
        // userDto.setPassword(user.getPassword());
        return userDto;
    }
}
