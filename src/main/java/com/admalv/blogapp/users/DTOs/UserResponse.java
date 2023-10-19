package com.admalv.blogapp.users.DTOs;

import lombok.Data;

@Data
public class UserResponse {
    Long id;
    String username;
    String bio;
    String email;
    String image;
}
