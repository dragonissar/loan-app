package com.devender.loan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devender.loan.entity.Loan;
import com.devender.loan.repository.CustomerRepository;
import com.devender.loan.repository.LoanRepository;

@Service
public class LoanService {

	@Autowired
	private LoanRepository loanRepository;
	@Autowired
	private CustomerRepository customerRepository;

	public Loan getLoan(Long id, Long loanId) {
		return loanRepository.findByIdAndCustomerId(loanId, id).get();
	}

	public Loan addLoan(Loan loan, Long id) {
		loan.setCustomer(customerRepository.findById(id).get());
		return loanRepository.save(loan);
	}

	public List<Loan> getAllLoans(Long id) {
		return loanRepository.findAllByCustomerId(id);
	}

}
