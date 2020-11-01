package com.project.orderingapp.exception;

public class OrderIdNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 124535888L;
	
	public OrderIdNotFoundException() {
		super("ID Not Found");
	}
}
