package com.devender.loan.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devender.loan.entity.Loan;

@RestController
public class AdminController {

	@PostMapping("/addNewLoan")
	public Loan postNewLoan() {
		return new Loan();
	}
}
