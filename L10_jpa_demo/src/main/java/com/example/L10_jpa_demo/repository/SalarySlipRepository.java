package com.example.L10_jpa_demo.repository;


import com.example.L10_jpa_demo.dbmodel.SalarySlip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalarySlipRepository extends JpaRepository<SalarySlip,Integer> {
}
