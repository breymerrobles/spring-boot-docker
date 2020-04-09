package com.globant.microservices.example.controller.errorhandler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@Getter
public class CustomErrorType {
	
	private HttpStatus status;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timestamp;
	private String errorMessage;

	public CustomErrorType(final String errorMessage, final HttpStatus status) {
		this.errorMessage = errorMessage;
		this.status = status;
		timestamp = LocalDateTime.now();
	}


}
