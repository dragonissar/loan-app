package com.devender.loan.error;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class ErrorDetails {

	private String reqDesc;
	private String description;
	private LocalDateTime timeStamp;
}
