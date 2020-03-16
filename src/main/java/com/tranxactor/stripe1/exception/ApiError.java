package com.tranxactor.stripe1.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class ApiError {
	
	private int status;
	private String message;
	private String developerMessage;

}
