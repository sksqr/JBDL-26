package com.geeksforgeeks.minor.l11_visitor_app.repos;

import com.geeksforgeeks.minor.l11_visitor_app.domain.Flats;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FlatsRepository extends JpaRepository<Flats, Long> {
}
