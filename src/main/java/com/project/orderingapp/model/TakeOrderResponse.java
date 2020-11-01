package com.project.orderingapp.model;

import io.swagger.v3.oas.annotations.media.Schema;

public class TakeOrderResponse {

	@Schema(description = "success status", example = "SUCCESS", required = true)
	private String status;

	public TakeOrderResponse(String status) {
		this.status = status;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
