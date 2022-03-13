package com.geeksforgeeks.minor.l13_visitor_app.repos;

import com.geeksforgeeks.minor.l13_visitor_app.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RolesRepository extends JpaRepository<Role, Long> {
}
