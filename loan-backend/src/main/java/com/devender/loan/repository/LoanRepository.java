package com.devender.loan.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devender.loan.entity.Loan;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
	List<Loan> findAllByCustomerId(Long customerId);

	Optional<Loan> findByIdAndCustomerId(Long id, Long customerId);
}