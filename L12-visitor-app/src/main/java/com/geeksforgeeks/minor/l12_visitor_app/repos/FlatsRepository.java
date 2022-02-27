package com.geeksforgeeks.minor.l12_visitor_app.repos;

import com.geeksforgeeks.minor.l12_visitor_app.domain.Flat;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FlatsRepository extends JpaRepository<Flat, Long> {
}
