package org.example.controller;

import org.example.entity.User;
import org.example.model.UserCreatedEvent;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public UserCreatedEvent createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PostMapping("/register")
    public UserCreatedEvent register(@RequestBody User dto) {
        return userService.registerUser(dto);
    }
}
