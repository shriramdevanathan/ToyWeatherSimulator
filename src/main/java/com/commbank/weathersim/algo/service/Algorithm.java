package com.commbank.weathersim.algo.service;

import java.util.List;

import com.commbank.weathersim.datamodel.MasterWeather;
import com.commbank.weathersim.datamodel.RegressionEstimateAttributes;
import com.commbank.weathersim.datamodel.WeatherAttributes;

public interface Algorithm {
	//Algo 1
	RegressionEstimateAttributes implementApacheRegression(List<WeatherAttributes> masterForAlgo) throws Exception;
	
	
	
	static double[] getY(List<WeatherAttributes> weatherAttrs){
		return weatherAttrs.stream().map(d->d.getTemperature()).mapToDouble(Double::doubleValue).toArray();
	}
	static double[][] getX(List<WeatherAttributes> weatherAttrs){
		return weatherAttrs.stream()
		        .map(p -> new double[] {p.getHumidity(),p.getPressure()
//		        		,p.getRainfall()
		        		})
		        .toArray(double[][]::new);
	}
	
	//Algo 2 could be JEMA
}
