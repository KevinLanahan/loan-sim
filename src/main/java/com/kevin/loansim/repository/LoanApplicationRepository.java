package com.kevin.loansim.repository;

import com.kevin.loansim.domain.LoanApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanApplicationRepository extends JpaRepository<LoanApplication, Long> {
    List<LoanApplication> findByEmailOrderByCreatedAtDesc(String email);
}
