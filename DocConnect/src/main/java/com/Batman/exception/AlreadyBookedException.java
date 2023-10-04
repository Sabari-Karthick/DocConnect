package com.Batman.exception;

public class AlreadyBookedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AlreadyBookedException(String message) {
		super(message);
	}

}
