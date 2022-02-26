package com.geeksforgeeks.minor.l11_visitor_app.repos;

import com.geeksforgeeks.minor.l11_visitor_app.domain.Roles;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RolesRepository extends JpaRepository<Roles, Long> {
}
