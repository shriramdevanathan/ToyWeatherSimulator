package com.commbank.weathersim.algo.service.impl;

import java.util.List;

import com.commbank.weathersim.algo.JAMAMultipleLinearRegression;
import com.commbank.weathersim.algo.service.Algorithm;
import com.commbank.weathersim.constants.AppConstants;
import com.commbank.weathersim.datamodel.RegressionEstimateAttributes;
import com.commbank.weathersim.datamodel.WeatherAttributes;

public class JamaAlgorithmImpl implements Algorithm{

	@Override
	public RegressionEstimateAttributes implementApacheRegression(List<WeatherAttributes> masterForAlgo)
			throws Exception {
		// TODO Auto-generated method stub
		JAMAMultipleLinearRegression regression = new JAMAMultipleLinearRegression(Algorithm.getX(masterForAlgo), Algorithm.getY(masterForAlgo));
		//double[] regParams = regression.beta(0) .estimateRegressionParameters();
		RegressionEstimateAttributes finalRegParams= new RegressionEstimateAttributes();
			finalRegParams.setReg2(regression.beta(0));
			finalRegParams.setReg3(regression.beta(1));
			finalRegParams.setReg1(regression.R2());
		//	finalRegParams.setReg4(regParams[3]);
		

		return finalRegParams;
	}

}
