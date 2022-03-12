package com.example.L15springsecurity.repositories;

import com.example.L15springsecurity.model.MyDBUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyDbUserRepository extends JpaRepository<MyDBUser,Long> {
     MyDBUser findByUsername(String username);
}
