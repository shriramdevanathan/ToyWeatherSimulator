package com.commbank.weathersim.constants;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * 
 * @author Shriram
 *
 */
public class AppConstants {
	static URI getURI(String name) throws URISyntaxException{
		return AppConstants.class.getResource(name).toURI();
	}
	private static final String path= "src/main/resources/";
	public static final String BOM_URL =  "http://www.bom.gov.au/climate/dwo/";
	public static final String RESOURCE_FOLDER = path + "";
	public static final String RESOURCE_FOLDER_DATA = path + "Data/";
	public static final String RESOURCE_FOLDER_LOCATION_DATA =path + "location/location.txt";
	public static final String RESOURCE_FOLDER_LOCATION_ALGO = path+"algorithm/algo-choice.txt";
	

	public static String USER_AGENT = "Mozilla/5.0";
	
	public static final Integer TEMPERATURE_INDEX = 10;
	public static final Integer PRESSURE_INDEX = 15;
	public static final Integer HUMIDITY_INDEX = 11;
	public static final Integer RAINFALL_INDEX = 4;
	public static final Integer DATE_INDEX = 1;
	public static final Integer NUMBER_OF_X = 3;
	public static final double HUMIDITY_LEVEL = 75D;
	public static final double SNOW_LEVEL = 0D ;
	public static final int NUMBER_OF_DAYS =  4 ;
	
	
}
