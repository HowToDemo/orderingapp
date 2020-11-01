package com.project.orderingapp.utility;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.project.orderingapp.exception.AppException;
import com.project.orderingapp.exception.InvalidParameterException;

@RunWith(SpringRunner.class)
public class OrderingAppUtilityTest {
	
	private List<String> validOrigin;
	private List<String> validDestination;
	private List<String> invalidSizeOrigin;
	private List<String> invalidSizeDestination;
	private List<String> invalidOriginValue;
	private List<String> invalidDestinationValue;
	
	@Before
	public void setUp() {
		validOrigin = new ArrayList<>();
		validOrigin.add("14.315601");
		validOrigin.add("120.9541");
		validDestination = new ArrayList<>();
		validDestination.add("14.359797");
		validDestination.add("121.05181");
		
		invalidSizeOrigin = new ArrayList<>();
		invalidSizeOrigin.add("14.3156012");
		invalidSizeOrigin.add("14.3156012");
		invalidSizeOrigin.add("14.3156012");
		invalidSizeDestination = new ArrayList<>();
		invalidSizeDestination.add("14.3156012");
		invalidSizeDestination.add("14.3156012");
		invalidSizeDestination.add("14.3156012");
		
		invalidOriginValue = new ArrayList<>();
		invalidOriginValue.add("10000.123567");
		invalidOriginValue.add("10000.123567");
		invalidDestinationValue = new ArrayList<>();
		invalidDestinationValue.add("10000.123567");
		invalidDestinationValue.add("10000.123567");
	}
	
	@Test
	public void whenConvertStringToListReturnList() {
		String sampleString = "['SAMPLE','SAMPLE']";
		List<String> mockList = OrderingAppUtil.convertStringToList(sampleString,",");
		assertNotEquals(mockList.size(),0);
	}
	
	@Test(expected=AppException.class)
	public void whenConvertStringToListWithNullValueExpectAppException() {
		OrderingAppUtil.convertStringToList(null,",");
	}
	
	@Test
	public void whenValidateCoordinatesReturnTrue() {
		assertTrue(OrderingAppUtil.validateCoordinates(validOrigin, validDestination));
	}
	
	@Test(expected=InvalidParameterException.class)
	public void whenValidateCoordinatesWithWrongArraySizeExpectInvalidParameterException() {
		OrderingAppUtil.validateCoordinates(invalidSizeOrigin, invalidSizeDestination);
	}
	
	@Test(expected=InvalidParameterException.class)
	public void whenValidateCoordinatesWithWrongValueExpectInvalidParameterException() {
		OrderingAppUtil.validateCoordinates(invalidOriginValue, invalidDestinationValue);
	}
}
