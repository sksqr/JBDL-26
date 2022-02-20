package com.example.L10_jpa_demo.repository;


import com.example.L10_jpa_demo.dbmodel.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person,Integer> {
}
