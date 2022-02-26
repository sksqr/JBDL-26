package com.geeksforgeeks.minor.l11_visitor_app.repos;

import com.geeksforgeeks.minor.l11_visitor_app.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AddressRepository extends JpaRepository<Address, Long> {
}
