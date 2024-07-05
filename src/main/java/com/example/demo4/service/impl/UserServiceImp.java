package com.example.demo4.service.impl;

import com.example.demo4.domain.User;
import com.example.demo4.repos.UserRepo;
import com.example.demo4.rest.dto.UserDtos.EditUserRequest;
import com.example.demo4.rest.dto.UserDtos.NewUserRequest;
import com.example.demo4.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.demo4.rest.dto.UserDtos.UserDto;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {
    public final UserRepo userRepo;

    @Override
    public List<UserDto> findAllUsers() {
        List<User> all = userRepo.findAll();
        return all.stream()
                .map(user -> UserDto.builder()
                        .username(user.getUsername())
                        .role(user.getRole()).build()).toList();
    }

    @Override
    public UserDto findById(Long id) {
        Optional<User> element = userRepo.findById(id);
        User user = element.get();
        return UserDto.builder()
                .username(user.getUsername())
                .role(user.getRole()).build();
    }

    @Override
    public Long addNewUser(NewUserRequest request) {
        if (!StringUtils.hasText(request.getUsername()) ||
                !StringUtils.hasText(request.getPassword())) {
            throw new IllegalArgumentException("Необходимо заполнить все поля");
        }
        if (request.getPassword().length() < 5) {
            throw new IllegalArgumentException("Пароль слишком слабый - необходимо как минимум 5 знаков");
        }
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        userRepo.saveAndFlush(user);
        return user.getId();

    }

    @Override
    public UserDto editUser(Long id, EditUserRequest request) {
        Optional<User> element = userRepo.findById(id);
        User user = element.get();
        user.setUsername(request.getUsername());
        user.setRole(request.getRole());
        userRepo.save(user);
        return UserDto.builder()
                .username(user.getUsername())
                .role(user.getRole()).build();
    }

    @Override
    public void deleteById(Long id) {
        userRepo.deleteById(id);
    }
}




