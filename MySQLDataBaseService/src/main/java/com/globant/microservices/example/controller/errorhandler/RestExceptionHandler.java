package com.globant.microservices.example.controller.errorhandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.globant.microservices.example.exception.ConflictException;
import com.globant.microservices.example.exception.NotFoundException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String error = "Malformed JSON request";
		return buildResponseEntity(HttpStatus.BAD_REQUEST, error, ex);
	}

	private ResponseEntity<Object> buildResponseEntity(HttpStatus status, String error, Exception ex) {
		logger.error("Error handler: {}", ex.getMessage(), ex);
		return new ResponseEntity<>(new CustomErrorType(error, status), status);
	}

	@ExceptionHandler(NotFoundException.class)
	protected ResponseEntity<Object> handleNotFoundException(NotFoundException ex) {
		return buildResponseEntity(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
	}

	@ExceptionHandler(ConflictException.class)
	protected ResponseEntity<Object> handleConflictException(ConflictException ex) {
		return buildResponseEntity(HttpStatus.CONFLICT, ex.getMessage(), ex);
	}


}
