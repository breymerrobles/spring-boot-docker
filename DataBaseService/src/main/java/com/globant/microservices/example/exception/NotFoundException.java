package com.globant.microservices.example.exception;

public class NotFoundException extends Exception {
	private static final long serialVersionUID = -2249736283502631807L;

	public NotFoundException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public NotFoundException(final String message) {
		super(message);
	}

	public NotFoundException(final Throwable cause) {
		super(cause);
	}
}
