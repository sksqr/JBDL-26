package com.example.L15springsecurity.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class MyDBUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;
    private String roles;

    transient private List<GrantedAuthority> authorityList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public void setRoles(String roles){
        this.roles=roles;
        String[] rolesArr = roles.split(",");
        authorityList = new ArrayList<>();
        for(String role : rolesArr){
            authorityList.add(new SimpleGrantedAuthority(role));
        }
    }

    @PostLoad
    private void populateAuthorities(){
        authorityList = new ArrayList<>();
        String[] rolesArr = roles.split(",");
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


    public void setPassword(String enpassword){
        password =enpassword;

    }


    public void setUsername(String username) {
        this.username = username;
    }
}