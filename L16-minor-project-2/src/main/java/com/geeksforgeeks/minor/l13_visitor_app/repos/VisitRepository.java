package com.geeksforgeeks.minor.l13_visitor_app.repos;

import com.geeksforgeeks.minor.l13_visitor_app.domain.Flat;
import com.geeksforgeeks.minor.l13_visitor_app.domain.Visit;
import com.geeksforgeeks.minor.l13_visitor_app.model.VisitStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface VisitRepository extends JpaRepository<Visit, Long> {
    List<Visit> findByflat(Flat flat);

    List<Visit> findByflatAndStatus(Flat flat, VisitStatus visitStatus);

}
