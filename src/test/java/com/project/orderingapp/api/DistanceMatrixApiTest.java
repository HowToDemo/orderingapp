package com.project.orderingapp.api;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.project.orderingapp.model.DistanceDurationObj;
import com.project.orderingapp.model.DistanceMatrixResponse;
import com.project.orderingapp.model.DistanceMatrixResponseRow;
import com.project.orderingapp.model.DistanceMatrixRowElement;

@RunWith(MockitoJUnitRunner.class)
public class DistanceMatrixApiTest {
	
	@InjectMocks
	private DistanceMatrixApi distanceMatrixApi;
	
	@Mock
	private RestTemplate restTemplate;

	private DistanceMatrixResponse response;
	
	private List<String> validOrigin;
	private List<String> validDestination;
	private List<String> invalidOriginValue;
	private List<String> invalidDestinationValue;
	
	@Before
	public void setUp() {
		validOrigin = new ArrayList<>();
		validOrigin.add("14.315601");
		validOrigin.add("120.95417");
		validDestination = new ArrayList<>();
		validDestination.add("14.359797");
		validDestination.add("121.05181");
		invalidOriginValue = new ArrayList<>();
		invalidOriginValue.add("INVALID");
		invalidOriginValue.add("INVALID");
		invalidDestinationValue = new ArrayList<>();
		invalidDestinationValue.add("INVALID");
		invalidDestinationValue.add("INVALID");
		
		response = new DistanceMatrixResponse();
		ArrayList<DistanceMatrixResponseRow> rows = new ArrayList<>();
		List<DistanceMatrixRowElement> elements = new ArrayList<>();
		DistanceMatrixRowElement elementObj = new DistanceMatrixRowElement();
		DistanceDurationObj distance = new DistanceDurationObj();
		distance.setText("SAMPLE");
		distance.setValue(Long.valueOf(22179));
		elementObj.setStatus("STATUS");
		elementObj.setDistance(distance);
		elements.add(elementObj);
		DistanceMatrixResponseRow rowResponse = new DistanceMatrixResponseRow();
		rowResponse.setElements(elements);
		rows.add(rowResponse);

		response.setRows(rows);
	}
	
	@Test
	public void whenGetTotalDistanceReturnInteger() {
		when(restTemplate.getForEntity(Mockito.anyString(), Mockito.any(),Mockito.anyMap())).thenReturn(new ResponseEntity<>(response,HttpStatus.OK));
		int mockTotalDistance = response.getRows().get(0).getElements().get(0).getDistance().getValue().intValue();
		int totalDistance = distanceMatrixApi.getTotalDistance(validOrigin, validDestination);
		assertEquals(totalDistance,mockTotalDistance);
	}
}
