package com.project.orderingapp.model;

import io.swagger.v3.oas.annotations.media.Schema;

public class ErrorResponse {

	@Schema(description = "error description", example = "ERROR_DESCRIPTION", required = true)
	private String error;

	public ErrorResponse() {
		
	}
	
	public ErrorResponse(String error) {
		this.error = error;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
}
