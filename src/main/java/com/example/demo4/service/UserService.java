package com.example.demo4.service;


import com.example.demo4.rest.dto.UserDtos.EditUserRequest;
import com.example.demo4.rest.dto.UserDtos.NewUserRequest;
import com.example.demo4.rest.dto.UserDtos.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> findAllUsers();
    UserDto findById(Long id);
    Long addNewUser(NewUserRequest request);
    UserDto editUser(Long id , EditUserRequest request);
    void deleteById(Long id);
}
