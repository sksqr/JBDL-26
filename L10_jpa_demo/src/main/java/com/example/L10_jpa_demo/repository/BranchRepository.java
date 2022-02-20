package com.example.L10_jpa_demo.repository;

import com.example.L10_jpa_demo.dbmodel.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepository extends JpaRepository<Branch,Integer> {
}
