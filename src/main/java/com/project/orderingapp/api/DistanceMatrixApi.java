package com.project.orderingapp.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.project.orderingapp.constant.AppConstant;
import com.project.orderingapp.exception.ApiResponseException;
import com.project.orderingapp.model.DistanceMatrixResponse;

@Component
public class DistanceMatrixApi {
	
	@Autowired
	private RestTemplate restTemplate;
	
	Logger logger = LoggerFactory.getLogger(DistanceMatrixApi.class);
	
	public Integer getTotalDistance(List<String> origin,List<String> destination) {
		Integer totalDistance = 0;
		Map<String, String> params = new HashMap<String, String>();
	    params.put("units", AppConstant.DISTANCE_MATRIX_API_UNITS);
	    params.put("origins", origin.get(AppConstant.LATITUDE)+ ","+origin.get(AppConstant.LONGITUDE));
	    params.put("destination", destination.get(AppConstant.LATITUDE)+ ","+destination.get(AppConstant.LONGITUDE));
	    params.put("key",AppConstant.DISTANCE_MATRIX_API_KEY);
	    
		ResponseEntity<DistanceMatrixResponse> apiResponse = restTemplate.getForEntity(AppConstant.DISTANCE_MATRIX_API_URL, DistanceMatrixResponse.class,params);
		DistanceMatrixResponse distanceMatrixResponse = apiResponse.getBody();
		String status = distanceMatrixResponse.getRows().get(0).getElements().get(0).getStatus();
		if(status.equalsIgnoreCase("NOT_FOUND")) {
			logger.error("Coordinates [{},{}] and [{},{}] is invalid.",origin.get(AppConstant.LATITUDE),origin.get(AppConstant.LONGITUDE),
				destination.get(AppConstant.LATITUDE),destination.get(AppConstant.LONGITUDE));
			throw new ApiResponseException();
		} else {
			totalDistance = distanceMatrixResponse.getRows().get(0).getElements().get(0).getDistance().getValue().intValue();
		}
		
		return totalDistance;
	}
}
