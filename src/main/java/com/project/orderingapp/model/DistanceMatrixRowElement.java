package com.project.orderingapp.model;

public class DistanceMatrixRowElement {

	private String status;
	private DistanceDurationObj distance;
	private DistanceDurationObj duration;
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public DistanceDurationObj getDistance() {
		return distance;
	}
	
	public void setDistance(DistanceDurationObj distance) {
		this.distance = distance;
	}
	
	public DistanceDurationObj getDuration() {
		return duration;
	}
	
	public void setDuration(DistanceDurationObj duration) {
		this.duration = duration;
	}
}
