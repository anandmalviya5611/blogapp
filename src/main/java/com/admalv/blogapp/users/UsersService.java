package com.admalv.blogapp.users;

import com.admalv.blogapp.users.DTOs.CreateUserRequest;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    private final UserRepository userRepository;

    public UsersService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity createUser(CreateUserRequest u){
        var user = UserEntity.builder()
                .username(u.getUsername())
        //        .password(password) // TODO encrypt password
                .email(u.getEmail()).build();

        return userRepository.save(user);
    }

    public UserEntity getUser(String username){
        return userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
    }
    public UserEntity getUser(Long userId){
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
    }

    public UserEntity loginUser(String username, String password){
        var user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));

        // TODO check password
        return user;
    }

    public static class UserNotFoundException extends IllegalArgumentException {
        public UserNotFoundException(String username) {
          super("User " + username + " not found");
        }

        public UserNotFoundException(Long userId) {
            super("User " + userId + " not found");
        }
    }
}
