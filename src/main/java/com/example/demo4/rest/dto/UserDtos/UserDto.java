package com.example.demo4.rest.dto.UserDtos;

import lombok.Builder;
import lombok.Data;
// дтошку эту делаем например чтобы можно было посмотреть всех пользователей списком
//личный кабинет типо
@Data
@Builder
public class UserDto {
    private String username;
    private String role;
}
