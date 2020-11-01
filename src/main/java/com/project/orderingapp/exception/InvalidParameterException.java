package com.project.orderingapp.exception;

public class InvalidParameterException extends RuntimeException {

	private static final long serialVersionUID = 12453L;
	
	public InvalidParameterException() {
		super("Invalid Parameters");
	}
}
