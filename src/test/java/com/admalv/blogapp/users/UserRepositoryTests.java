package com.admalv.blogapp.users;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
public class UserRepositoryTests {
    @Autowired
    private UserRepository userRepository;

    @Test
    @Order(1)
    void can_create_user(){
        var user = UserEntity.builder()
                .username("andyu")
                .email("andyu@mandyu.com")
                .build();

        userRepository.save(user);
    }

    @Test
    @Order(2)
    void find_User(){
        var user = UserEntity.builder()
                .username("andyu")
                .email("andyu@mandyu.com")
                .build();

        userRepository.save(user);
        var allUsers = userRepository.findAll();
        Assertions.assertEquals(1, allUsers.size());
    }

}
