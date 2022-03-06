package com.example.L14_springsecurity_demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloController {

    @GetMapping("/hello")
    public ResponseEntity<String> getHello(){
        return ResponseEntity.ok("HELLO!");
    }


    @GetMapping("/helloUser")
    public ResponseEntity<String> getHelloUser(){
        return ResponseEntity.ok("HELLO! User");
    }
}

//6A3B6642B1CA9210F42698A3020B98B8
