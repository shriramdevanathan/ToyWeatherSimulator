package com.commbank.weathersim.service;

import java.io.IOException;
import java.util.List;

import com.commbank.weathersim.datamodel.MasterWeather;

public interface RegressionParameterService {
	List<String> estimateRegressionParameters(MasterWeather mw) throws IOException, Exception;
}
