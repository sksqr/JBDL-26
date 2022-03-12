package com.example.L15springsecurity.services;

import com.example.L15springsecurity.model.MyDBUser;
import com.example.L15springsecurity.repositories.MyDbUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Primary
public class MyDBUserService implements UserDetailsService {

    @Autowired
    private MyDbUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyDBUser myDBUser = userRepository.findByUsername(username);
        if(myDBUser == null){
            throw new UsernameNotFoundException("User does not exist "+username);
        }
        return (UserDetails)myDBUser;
    }
}
