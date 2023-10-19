package com.admalv.blogapp.users;

import com.admalv.blogapp.commonDTO.ErrorResponse;
import com.admalv.blogapp.users.DTOs.CreateUserRequest;
import com.admalv.blogapp.users.DTOs.UserResponse;
import com.admalv.blogapp.users.DTOs.LoginUserRequest;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UsersController {
    private final UsersService usersService;
    private final ModelMapper modelMapper;
    public UsersController(UsersService usersService, ModelMapper modelMapper) {
        this.usersService = usersService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/signup")
    ResponseEntity<UserResponse> signupUser(@RequestBody CreateUserRequest request){
        var response = usersService.createUser(request);
        var user = modelMapper.map(response, UserResponse.class);
        URI responseUri = URI.create("/users/"+ response.getId());
        return ResponseEntity.created(responseUri).body(user);
    }

    @PostMapping("/login")
    ResponseEntity<UserResponse> loginUser(@RequestBody LoginUserRequest request) {
        var response = usersService.loginUser(request.getUsername(), request.getPassword());
        var user = modelMapper.map(response, UserResponse.class);
        return ResponseEntity.ok(user);

    }

    @ExceptionHandler({
            UsersService.UserNotFoundException.class
    })
    ResponseEntity<ErrorResponse> handleUserNotFoundException(Exception ex){
        String message;
        HttpStatus status;
        if(ex instanceof UsersService.UserNotFoundException){
            message = ex.getMessage();
            status = HttpStatus.NOT_FOUND;
        }else{
            message = "something went wrong!";
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        ErrorResponse internalServerError = ErrorResponse.builder()
                .message(message)
                .build();

        return ResponseEntity.status(status).body(internalServerError);
    }

}
