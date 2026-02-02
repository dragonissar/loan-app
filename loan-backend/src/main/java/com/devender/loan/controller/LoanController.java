package com.devender.loan.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devender.loan.entity.Customer;
import com.devender.loan.entity.Loan;
import com.devender.loan.service.CustomerService;
import com.devender.loan.service.LoanService;

@RestController
public class LoanController {

	@Autowired
	private LoanService loanService;
	@Autowired
	private CustomerService customerService;

	@GetMapping(path = "/customers/{id}", produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	public Customer getCustomerDetails(@PathVariable Long id) {
		return customerService.getCustomerDetails(id);
	}

	@GetMapping("{id}/loans")
	public List<Loan> getAllLoans(@PathVariable Long id) {
		return loanService.getAllLoans(id);
	}

	@GetMapping(path = "{id}/loans/{loanId}", produces = "application/hal+json")
	public EntityModel<Loan> getLoan(@PathVariable Long loanId, @PathVariable Long id) {
		Loan loan = loanService.getLoan(id, loanId);
		Link self = linkTo(methodOn(LoanController.class).getLoan(loanId, id)).withSelfRel();
		Link allLoans = linkTo(methodOn(LoanController.class).getAllLoans(id)).withRel("all-loans");
		Link customer = linkTo(methodOn(LoanController.class).getCustomerDetails(id)).withRel("customer");
		EntityModel<Loan> em = EntityModel.of(loan, self, allLoans, customer);
		return em;
	}

	@PostMapping("/{id}/loans")
	public ResponseEntity<Loan> addLoan(@RequestBody Loan loan, @PathVariable Long id) {
		Loan newLoan = loanService.addLoan(loan, id);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/%d/loans/%d")
				.buildAndExpand(id, newLoan.getId()).toUri();
		return ResponseEntity.created(uri).body(newLoan);
	}

}
