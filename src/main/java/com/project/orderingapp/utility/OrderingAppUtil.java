package com.project.orderingapp.utility;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.project.orderingapp.constant.AppConstant;
import com.project.orderingapp.exception.AppException;
import com.project.orderingapp.exception.InvalidParameterException;

public class OrderingAppUtil {

	public static List<String> convertStringToList(String value,String regex) {
		if(value == null) {
			throw new AppException("Method(convertStringToList) cannot be empty");
		} else {
			String[] array = value.replaceAll("[\\[\\]\\']", "").split(regex);
			List<String> arrayList = new ArrayList<>();
			for(String arrayValue : array) {
				arrayList.add(arrayValue);
			}
			return arrayList;
		}
	}
	
	public static Boolean validateCoordinates(List<String> origin,List<String> destination) {
		Boolean output = true;
		BigDecimal originLangitude = new BigDecimal(origin.get(AppConstant.LATITUDE));
		BigDecimal originLongitude = new BigDecimal(origin.get(AppConstant.LONGITUDE));
		BigDecimal destinationLangitude = new BigDecimal(destination.get(AppConstant.LATITUDE));
		BigDecimal destinationLongitude = new BigDecimal(destination.get(AppConstant.LONGITUDE));
		
		if(origin.size() != 2 || destination.size() != 2) {
			throw new InvalidParameterException();
		} else {
			if(originLangitude.intValue() < -90 || originLangitude.intValue() > 90 || destinationLangitude.intValue() < -90 || destinationLangitude.intValue() > 90
				|| originLongitude.intValue() < -180 || originLongitude.intValue() > 180 || destinationLongitude.intValue() < -180 || destinationLongitude.intValue() > 180) {
				throw new InvalidParameterException();
			}
		}
		return output;
	}
}
