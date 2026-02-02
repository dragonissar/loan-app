package com.devender.loan.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

import com.devender.loan.entity.Customer;
import com.devender.loan.entity.Loan;
import com.devender.loan.entity.LoanStatus;
import com.devender.loan.entity.LoanType;
import com.devender.loan.error.ErrorDetails;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class EndToEndApiTests {

	@Autowired
	private TestRestTemplate webClient;
	private Loan loan;

	private Loan createTestLoan() {
		Loan loan = new Loan();
		loan.setDate(LocalDate.now().minusYears(2));
		loan.setEmi(10000.0);
		loan.setInterestRate(10.0);
		loan.setStatus(LoanStatus.ACTIVE.name());
		loan.setTotal(250000.0);
		loan.setType(LoanType.PERSONAL_LOAN.name());
		return loan;
	}

	@Test
	@DisplayName("Should successfully create a loan and retrieve it")
	void testAddLoan() {
		loan = createTestLoan();
		ResponseEntity<Loan> response = webClient.postForEntity("/1/loans", loan, Loan.class);
		assertEquals(HttpStatus.CREATED, response.getStatusCode(), "Should return 201 status for created loan");
		Loan body = response.getBody();
		assertNotNull(body.getId(), "Generated ID should not be null");
		Loan newLoan = webClient.getForEntity(String.format("/1/loans/%d", body.getId()), Loan.class).getBody();
		assertNotNull(newLoan);
		Customer customer = webClient.getForEntity("/customers/1", Customer.class).getBody();
		assertNotNull(customer);
		assertTrue(customer.getLoans().stream().anyMatch(lo -> lo.getId() == body.getId()));
	}

	@Test
	@DisplayName("Should retrieve list of loans for a customer")
	void testGetAllLoans() {
		ParameterizedTypeReference<List<Loan>> loanListType = new ParameterizedTypeReference<List<Loan>>() {
		};
		List<Loan> newLoanList = webClient.exchange("/1/loans", HttpMethod.GET, null, loanListType).getBody();
		assertNotNull(newLoanList, "Loan list should not be null");
	}

	@Test
	@DisplayName("Should retrieve details of a customer")
	void testGetCustomer() {
		Customer customer = webClient.getForEntity("/customers/1", Customer.class).getBody();
		assertNotNull(customer);
	}

	//@Test
	@DisplayName("Should throw error")
	void testError() {
		ErrorDetails errorDetails = webClient.getForEntity("/unknown_url", ErrorDetails.class).getBody();
		System.out.println(errorDetails);
		assertNotNull(errorDetails);
	}

	@Test
	@DisplayName("Should retrieve xml response")
	void testGetCustomerXml() {
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_XML_VALUE);
		HttpEntity<Customer> reqEntity = new HttpEntity<>(headers);
		ResponseEntity<Customer> response = webClient.exchange("/customers/1", HttpMethod.GET, reqEntity,
				Customer.class);
		assertEquals(MediaType.APPLICATION_XML, response.getHeaders().getContentType());
		Customer customer = response.getBody();
		assertNotNull(customer);
	}
}
