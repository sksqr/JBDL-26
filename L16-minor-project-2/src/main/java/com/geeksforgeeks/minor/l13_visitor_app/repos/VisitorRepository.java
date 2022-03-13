package com.geeksforgeeks.minor.l13_visitor_app.repos;

import com.geeksforgeeks.minor.l13_visitor_app.domain.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;


public interface VisitorRepository extends JpaRepository<Visitor, Long> {
    Visitor findByPhone(String phone);
    Visitor findByEmail(String email);
}
