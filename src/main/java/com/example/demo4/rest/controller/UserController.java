package com.example.demo4.rest.controller;

import com.example.demo4.rest.dto.UserDtos.EditUserRequest;
import com.example.demo4.rest.dto.UserDtos.NewUserRequest;
import com.example.demo4.rest.dto.UserDtos.UserDto;
import com.example.demo4.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDto>> getAll() //<List<UserDto>> - озвращаемый на запрос список пользователей в формате DTO
    {
        return ResponseEntity.ok(userService.findAllUsers());
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getById(@PathVariable Long id)
    {
        return ResponseEntity.ok(userService.findById(id));
    }


    @PostMapping
    public ResponseEntity<Long> add(@RequestBody NewUserRequest request) // Long - возвращаем индекс добавленного пользователя
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.addNewUser(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id)
    {
        userService.deleteById(id);
        return ResponseEntity.ok().build();
    }
    @PatchMapping("/{id}")
    public ResponseEntity<UserDto> edit(@PathVariable Long id,@RequestBody EditUserRequest request )
    {


        return ResponseEntity.ok( userService.editUser(id, request));

        //возвращаем измененного пользователя: имя роль
    }



}
