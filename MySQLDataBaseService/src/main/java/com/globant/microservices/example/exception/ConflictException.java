package com.globant.microservices.example.exception;

public class ConflictException extends Exception {
	private static final long serialVersionUID = -2249736283502631807L;

	public ConflictException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public ConflictException(final String message) {
		super(message);
	}

	public ConflictException(final Throwable cause) {
		super(cause);
	}

}
