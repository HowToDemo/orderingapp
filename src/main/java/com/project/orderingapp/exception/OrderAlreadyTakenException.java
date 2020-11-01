package com.project.orderingapp.exception;

public class OrderAlreadyTakenException extends RuntimeException {

	private static final long serialVersionUID = 1245354L;
	
	public OrderAlreadyTakenException() {
		super("Order Already Taken");
	}
}
