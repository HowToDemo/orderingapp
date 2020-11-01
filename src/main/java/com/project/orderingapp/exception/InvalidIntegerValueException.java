package com.project.orderingapp.exception;

public class InvalidIntegerValueException extends RuntimeException {
	
	private static final long serialVersionUID = 124536753L;
	
	public InvalidIntegerValueException() {
		super("Value must be 1 or higher");
	}
}
