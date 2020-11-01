package com.project.orderingapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="customer_order")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="origin_latitude")
	private String originLatitude;
	
	@Column(name="origin_longitude")
	private String originLongitude;
	
	@Column(name="destination_latitude")
	private String destinationLatitude ;
	
	@Column(name="distance")
	private Integer distance;
	
	@Column(name="destination_longitude")
	private String destinationLongitude;
	
	private String status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOriginLatitude() {
		return originLatitude;
	}

	public void setOriginLatitude(String originLatitude) {
		this.originLatitude = originLatitude;
	}

	public String getOriginLongitude() {
		return originLongitude;
	}

	public void setOriginLongitude(String originLongitude) {
		this.originLongitude = originLongitude;
	}

	public String getDestinationLatitude() {
		return destinationLatitude;
	}

	public void setDestinationLatitude(String destinationLatitude) {
		this.destinationLatitude = destinationLatitude;
	}

	public Integer getDistance() {
		return distance;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	public String getDestinationLongitude() {
		return destinationLongitude;
	}

	public void setDestinationLongitude(String destinationLongitude) {
		this.destinationLongitude = destinationLongitude;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
