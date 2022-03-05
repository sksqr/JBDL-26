package com.geeksforgeeks.minor.l13_visitor_app.repos;

import com.geeksforgeeks.minor.l13_visitor_app.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsersRepository extends JpaRepository<User, Long> {
}
