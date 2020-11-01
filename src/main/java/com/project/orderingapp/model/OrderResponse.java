package com.project.orderingapp.model;

import io.swagger.v3.oas.annotations.media.Schema;

public class OrderResponse {

	@Schema(description = "<Order_Id>", example = "1")
	private Long id;
	@Schema(description = "<total_distance>", example = "200.000")
	private Integer distance;
	@Schema(description = "<order_status>", example = "UNASSIGNED")
	private String status;
	
	public OrderResponse() {
	}
	
	public OrderResponse(String status) {
		this.status = status;
	}
	
	public OrderResponse(Long id,Integer distance,String status) {
		this.id = id;
		this.distance = distance;
		this.status = status;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Integer getDistance() {
		return distance;
	}
	
	public void setDistance(Integer distance) {
		this.distance = distance;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
}
