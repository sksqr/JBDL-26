package com.geeksforgeeks.minor.l11_visitor_app.repos;

import com.geeksforgeeks.minor.l11_visitor_app.domain.Visit;
import org.springframework.data.jpa.repository.JpaRepository;


public interface VisitRepository extends JpaRepository<Visit, Long> {
}
