package com.example.L15springsecurity.controller;

import com.example.L15springsecurity.model.MyDBUser;
import com.example.L15springsecurity.repositories.MyDbUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

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




//for CSRF Demo
    @GetMapping("/admin/changePassword")
    public ResponseEntity<String> getCreateUser(@RequestParam String newPassword){
        UserDetails userDetails  = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        MyDBUser user = repository.findByUsername(userDetails.getUsername());
        user.setPassword(passwordEncoder.encode(newPassword));
        repository.save(user);
        return ResponseEntity.ok("Password changed");
    }

}
