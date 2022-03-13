package com.geeksforgeeks.minor.l13_visitor_app.service;

import com.geeksforgeeks.minor.l13_visitor_app.domain.User;
import com.geeksforgeeks.minor.l13_visitor_app.repos.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = usersRepository.findByEmail(username);
        if(user == null){
            throw new UsernameNotFoundException("No user with username "+username);
        }
        return user;
    }
}
