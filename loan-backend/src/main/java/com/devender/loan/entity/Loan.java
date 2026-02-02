package com.devender.loan.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Past;
import lombok.Data;

@Data
@Entity
public class Loan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "customer_id")
	@JsonBackReference
	private Customer customer;
	private String type;
	@Past(message = "Loan date should be in past")
	private LocalDate date;
	@Min(value = 1000, message = "Minimum loan amount should be 1000")
	private double total;
	private double interestRate;
	private double emi;
	private String status;

}
