package com.project.orderingapp.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.ToString;

@ToString
public class OrderRequest {

	@Schema(description = "Origin coordinates", example = "['START_LATITUDE','START_LONGITUDE']", required = true)
	@JsonProperty("origin")
	@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
	private List<String> origin;
	
	@Schema(description = "Destination coordinates", example = "['END_LATITUDE','END_LONGITUDE']", required = true)
	@JsonProperty("destination")
	@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
	private List<String> destination;

	public List<String> getOrigin() {
		return origin;
	}

	public void setOrigin(List<String> origin) {
		this.origin = origin;
	}

	public List<String> getDestination() {
		return destination;
	}

	public void setDestination(List<String> destination) {
		this.destination = destination;
	}
}
