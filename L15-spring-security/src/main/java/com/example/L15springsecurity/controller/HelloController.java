package com.example.L15springsecurity.controller;

import com.example.L15springsecurity.model.MyDBUser;
import com.example.L15springsecurity.repositories.MyDbUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MyDbUserRepository repository;

    @GetMapping("/admin/hello")
    public ResponseEntity<String> getHello(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok("HELLO! Admin "+userDetails.getUsername());
    }


    @GetMapping("/user/hello")
    public ResponseEntity<String> getHelloUser(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok("HELLO! User "+userDetails.getUsername());
    }

    @PostMapping("/admin/createUser")
    public ResponseEntity<Long> createUser(@RequestBody MyDBUser user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user = repository.save(user);
        return ResponseEntity.ok(user.getId());
    }

    @GetMapping("/admin/createUser")
    public ResponseEntity<String> getCreateUser(@RequestBody MyDBUser user){
        return ResponseEntity.ok("HELLO! CreateUser ");
    }

}
