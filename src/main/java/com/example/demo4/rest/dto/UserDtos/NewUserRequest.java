package com.example.demo4.rest.dto.UserDtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NewUserRequest {
    private String username;
    private String password;

}
