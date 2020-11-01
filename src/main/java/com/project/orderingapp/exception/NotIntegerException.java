package com.project.orderingapp.exception;

public class NotIntegerException extends RuntimeException {

	private static final long serialVersionUID = 1245367L;
	
	public NotIntegerException() {
		super("Parameters must be an integer");
	}
}
