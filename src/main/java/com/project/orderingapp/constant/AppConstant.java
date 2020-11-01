package com.project.orderingapp.constant;


public class AppConstant {

	public final static int LATITUDE = 0;
	public final static int LONGITUDE = 1;
	
	public final static String ORDER_SUCCESS_STATUS = "SUCCESS";
	public final static String ORDER_TAKEN_STATUS = "TAKEN";
	public final static String ORDER_UNASSIGNED_STATUS = "UNASSIGNED";
	
	public final static String DISTANCE_MATRIX_API_URL = "https://maps.googleapis.com/maps/api/distancematrix/json?units={units}&origins={origins}&destinations={destination}&key={key}";
	public final static String DISTANCE_MATRIX_API_KEY = "AIzaSyB1kvAJG6g3PHdlgjk7fl8tLl1YZL1p7Ww";
	public final static String DISTANCE_MATRIX_API_UNITS = "imperial";
	
}
