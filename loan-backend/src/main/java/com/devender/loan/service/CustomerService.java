package com.devender.loan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devender.loan.entity.Customer;
import com.devender.loan.repository.CustomerRepository;

@Service
public class CustomerService {
	@Autowired
	private CustomerRepository customerRepository;

	public CustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public Customer getCustomerDetails(Long id) {
			return customerRepository.findById(id).get();
	}

}
