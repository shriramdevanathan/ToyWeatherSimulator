package com.commbank.weathersim.service;

import java.io.IOException;

import com.commbank.weathersim.datamodel.MasterWeather;

public interface ToySim {
	void simulateForGame(MasterWeather mw)  throws IOException;
}
