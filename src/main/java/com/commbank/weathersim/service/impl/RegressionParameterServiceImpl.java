package com.commbank.weathersim.service.impl;

import java.io.IOException;
import java.util.List;

import com.commbank.weathersim.datamodel.MasterWeather;
import com.commbank.weathersim.service.RegressionParameterService;
import com.commbank.weathersim.service.TrainingDataService;

public class RegressionParameterServiceImpl implements RegressionParameterService {
	
	private static void calculateRegressionParams(MasterWeather masterWeather){
		
	}
	
	/**
	 * @author Shriram
	 * @throws Exception 
	 * 
	 */
	@Override
	public List<String> estimateRegressionParameters(final MasterWeather mw) throws Exception {
		TrainingDataService tdService = new TrainingDataServiceImpl();
		tdService.prepareTrainingData(mw);
		tdService.trainData(mw);
		
		//mw.displayCityRegressionParams();
		return null;
	}

}
