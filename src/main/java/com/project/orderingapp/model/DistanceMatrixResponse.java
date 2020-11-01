package com.project.orderingapp.model;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.ToString;

@ToString
public class DistanceMatrixResponse {

	@JsonProperty("destination_addresses")
	private ArrayList<String> destinationAddresses;
	@JsonProperty("origin_addresses")
	private ArrayList<String> originAddress;
	private ArrayList<DistanceMatrixResponseRow> rows;
	
	public ArrayList<String> getDestinationAddresses() {
		return destinationAddresses;
	}

	public void setDestinationAddresses(ArrayList<String> destinationAddresses) {
		this.destinationAddresses = destinationAddresses;
	}

	public ArrayList<String> getOriginAddress() {
		return originAddress;
	}
	
	public void setOriginAddress(ArrayList<String> originAddress) {
		this.originAddress = originAddress;
	}
	
	public ArrayList<DistanceMatrixResponseRow> getRows() {
		return rows;
	}
	
	public void setRows(ArrayList<DistanceMatrixResponseRow> rows) {
		this.rows = rows;
	}
}
