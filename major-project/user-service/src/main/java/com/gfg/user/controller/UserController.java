package com.gfg.user.controller;

import com.gfg.user.UserCreateRequest;
import com.gfg.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    ResponseEntity<Long> createUser(@RequestBody UserCreateRequest userCreateRequest) throws ExecutionException, InterruptedException {
        Long userId = userService.createUser(userCreateRequest);
        return ResponseEntity.ok(userId);
    }
}
