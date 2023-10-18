package com.admalv.blogapp.users;

import com.admalv.blogapp.users.DTOs.CreateUserRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class UsersServiceTests {

    @Autowired
    UsersService usersService;

    @Test
    void can_create_users(){
        var user = usersService.createUser(new CreateUserRequest(
                "andyu",
                "1234",
                "andyu@mandyu.com"
        ));
        Assertions.assertNotNull(user);
        Assertions.assertEquals("andyu", user.getUsername());
    }
}
