package com.project.orderingapp.exception;

public class ApiResponseException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ApiResponseException() {
		super("Failed To retrieve data from API");
	}
}
