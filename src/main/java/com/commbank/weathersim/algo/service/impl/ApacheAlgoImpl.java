package com.commbank.weathersim.algo.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;

import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;

import com.commbank.weathersim.algo.service.Algorithm;
import com.commbank.weathersim.constants.AppConstants;
import com.commbank.weathersim.datamodel.MasterWeather;
import com.commbank.weathersim.datamodel.RegressionEstimateAttributes;
import com.commbank.weathersim.datamodel.WeatherAttributes;

public class ApacheAlgoImpl implements Algorithm {

	@Override
	public RegressionEstimateAttributes implementApacheRegression(List<WeatherAttributes> weatherAtts) throws Exception {
		// TODO Auto-generated method stub
		List<RegressionEstimateAttributes> regAttrs = new ArrayList<RegressionEstimateAttributes>();
		OLSMultipleLinearRegression multipleLinearRegression = new OLSMultipleLinearRegression();
		multipleLinearRegression.newSampleData(Algorithm.getY(weatherAtts),Algorithm.getX(weatherAtts));
		
		double[] regParams = multipleLinearRegression.estimateRegressionParameters();
		RegressionEstimateAttributes finalRegParams= new RegressionEstimateAttributes();
		if(regParams.length == AppConstants.NUMBER_OF_X){
			finalRegParams.setReg1(regParams[0]);
			finalRegParams.setReg2(regParams[1]);
			finalRegParams.setReg3(regParams[2]);
		//	finalRegParams.setReg4(regParams[3]);
		}
		

		return finalRegParams;
		
	}

}
