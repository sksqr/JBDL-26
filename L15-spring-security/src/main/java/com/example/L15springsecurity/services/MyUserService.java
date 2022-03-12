package com.example.L15springsecurity.services;

import com.example.L15springsecurity.model.MyUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class MyUserService implements UserDetailsService {

    private Map<String, MyUser> userMap;

    @PostConstruct
    void init(){
        userMap =  new HashMap<>();
        userMap.put("shashi", new MyUser("shashi","$2a$10$Q/y0u6yk.UoeHbJCz1McS.xf7M7zXMRhNBKwDelHYX4Y3muS3KxJ6","user"));
        userMap.put("ravi", new MyUser("ravi","$2a$10$Q/y0u6yk.UoeHbJCz1McS.xf7M7zXMRhNBKwDelHYX4Y3muS3KxJ6","admin"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = userMap.get(username);
        if(userDetails == null){
            throw  new UsernameNotFoundException("User does not exist "+username);
        }
        return userMap.get(username);
    }
}
