package com.geeksforgeeks.minor.l12_visitor_app.repos;

import com.geeksforgeeks.minor.l12_visitor_app.domain.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;


public interface VisitorRepository extends JpaRepository<Visitor, Long> {
}
