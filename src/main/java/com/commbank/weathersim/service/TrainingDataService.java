package com.commbank.weathersim.service;

import java.io.IOException;

import com.commbank.weathersim.datamodel.MasterWeather;

public interface TrainingDataService {
	void prepareTrainingData( MasterWeather master) throws IOException;
	void trainData( MasterWeather masterData) throws IOException, Exception;
}

