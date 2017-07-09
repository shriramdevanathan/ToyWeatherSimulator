package com.commbank.weathersim.datamodel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
/**
 * 
 * @author Shriram
 *
 */
public class MasterWeather {
	/**
	 * Contains city as key and list.
	 */
	private Map<String,List<WeatherAttributes>> cityData;
	
	private Map<String,RegressionEstimateAttributes> cityRegParams = new HashMap<String,RegressionEstimateAttributes>();

	public Map<String, RegressionEstimateAttributes> getCityRegParams() {
		return cityRegParams;
	}

	public void setCityRegParams(Map<String, RegressionEstimateAttributes> cityRegParams) {
		this.cityRegParams = cityRegParams;
	}

	public Map<String, List<WeatherAttributes>> getCityData() {
		return cityData;
	}

	public void setCityData(Map<String, List<WeatherAttributes>> cityData) {
		this.cityData = cityData;
	}
	
	public void displayCityRegressionParams(){
		/**
		 * In memory of Java 7 :)
		 */
		
		for(Entry<String, RegressionEstimateAttributes> entry:cityRegParams.entrySet()){
			System.out.println("Key : "+ entry.getKey()+ " --> Value: " + entry.getValue().toString());
		}
	}

}
