package com.Auth_Server_Spring_Boot.controller;

import com.Auth_Server_Spring_Boot.businesslogic.UserInfo;
import com.Auth_Server_Spring_Boot.businesslogic.database.User;
import com.Auth_Server_Spring_Boot.businesslogic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserRestController {
    private UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public User addUser(@RequestBody UserInfo userInfo) {
        // check if user exists(username must be unique)
        User user = userService.getUser(userInfo.username());
        if (user != null) {
            throw new UserAlreadyExistsException("User with username '" + userInfo.username() + "' already exists");
        }

        // add user
        return userService.registerUser(userInfo);
    }

}
