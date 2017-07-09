package com.commbank.weathersim.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.commbank.weathersim.algo.AlgoFactory;
import com.commbank.weathersim.algo.service.Algorithm;
import com.commbank.weathersim.algo.service.impl.ApacheAlgoImpl;
import com.commbank.weathersim.constants.AppConstants;
import com.commbank.weathersim.data.FileReadUtil;
import com.commbank.weathersim.datamodel.MasterWeather;
import com.commbank.weathersim.datamodel.RegressionEstimateAttributes;
import com.commbank.weathersim.datamodel.WeatherAttributes;
import com.commbank.weathersim.service.TrainingDataService;

public class TrainingDataServiceImpl implements TrainingDataService {

	@Override
	public void prepareTrainingData(final MasterWeather master) throws IOException {
		// TODO Auto-generated method stub
		master.setCityData(new FileReadUtil().readFilesAndPopulate());
		
	}
	
	
	private static RegressionEstimateAttributes getCityRegParamsForCity(List<WeatherAttributes> weatherAttrs) throws Exception{
		
		Algorithm algo = AlgoFactory.getAlgoFactory(FileReadUtil.getAlgoType(AppConstants.RESOURCE_FOLDER_LOCATION_ALGO));
		return algo.implementApacheRegression(weatherAttrs);
		
	}
	@Override
	public void trainData(final MasterWeather masterData) throws Exception {
		// TODO Auto-generated method stub
		masterData.getCityData().forEach((k,v) -> {
			try {
				masterData.getCityRegParams().put(k,getCityRegParamsForCity(v));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
	}

}
