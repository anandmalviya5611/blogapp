package com.admalv.blogapp.users.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class LoginUserRequest {
    @NonNull
    private String username;
    @NonNull
    private String password;

}
