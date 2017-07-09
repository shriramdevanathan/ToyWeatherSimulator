package com.commbank.weathersim.mainapp;
import java.io.IOException;

import org.apache.commons.math3.stat.regression.MultipleLinearRegression;
import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;
import org.apache.commons.math3.stat.regression.SimpleRegression;

import com.commbank.weathersim.data.DataUtil;
import com.commbank.weathersim.datamodel.MasterWeather;
import com.commbank.weathersim.service.RegressionParameterService;
import com.commbank.weathersim.service.ToySim;
import com.commbank.weathersim.service.impl.RegressionParameterServiceImpl;
import com.commbank.weathersim.service.impl.ToySimImpl;

public class MainApp {


	    public static void main(String[] args) throws Exception {
	    	/**
	    	 * Get the Data from BOM website. If you need to add more cities, 
	    	 * just add it to the properties file cities-list.properties along with the key.
	    	 * If the city data has already been collected, it will skip collecting data from it.
	    	 * 
	    	 * TODO:automate id collection process as well. Currently, requires a bit of manual work to collect the ID 
	    	 */
	    	new DataUtil().populateDataFromBOMApi();

	    	MasterWeather masterWeather = new MasterWeather();
	    	try{
	    		
	    		/**
		    	 * Get the regression params for each city which includes converting to data that will be fed to 
		    	 * our algo
		    	 */
		    	RegressionParameterService regServiceImpl = new RegressionParameterServiceImpl();
		    	regServiceImpl.estimateRegressionParameters(masterWeather);
		    	ToySim toySimImpl = new ToySimImpl();
		    	toySimImpl.simulateForGame(masterWeather);
	    	}
	    	catch(Exception e){
	    		e.printStackTrace();
	    		masterWeather.displayCityRegressionParams();
	    		throw e;
	    	}
	    	
	    }

	       
	}

