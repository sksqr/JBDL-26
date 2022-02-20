package com.example.L10_jpa_demo.repository;


import com.example.L10_jpa_demo.dbmodel.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address,Integer> {
}
