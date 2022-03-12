package com.example.L15springsecurity.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class MyUser implements UserDetails {

    private String username;
    private String password;
    private String roles;

    private List<GrantedAuthority> authorityList;

    public MyUser(String username, String password, String roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
        String[] rolesArr = roles.split(",");
        authorityList = new ArrayList<>();
        for(String role : rolesArr){
            authorityList.add(new SimpleGrantedAuthority(role));
        }

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorityList;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
