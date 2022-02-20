package com.example.L9_jpa_demo.repository;

import com.example.L9_jpa_demo.dbmodel.SalarySlip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalarySlipRepository extends JpaRepository<SalarySlip,Integer> {
}
