package com.commbank.weathersim.service.impl;

import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.math3.genetics.TournamentSelection;

import com.commbank.weathersim.algo.Statistics;
import com.commbank.weathersim.constants.AppConstants;
import com.commbank.weathersim.constants.Condition;
import com.commbank.weathersim.data.DataUtil;
import com.commbank.weathersim.datamodel.MasterWeather;
import com.commbank.weathersim.datamodel.RegressionEstimateAttributes;
import com.commbank.weathersim.datamodel.ToySimulator;
import com.commbank.weathersim.datamodel.WeatherAttributes;
import com.commbank.weathersim.service.ToySim;

public class ToySimImpl implements ToySim {
	
	
	private static double getRandomDoubleInRage(double rangeMin, double rangeMax){
		Random r = new Random();
		double randomValue = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
		return randomValue;
	}
	
	private static WeatherAttributes getDefaultAttributesHardCoded(){
		/**
		 * No data available even in historical data
		 */
		WeatherAttributes defaultWeatherAttrsIfNoData = new WeatherAttributes();
		
		defaultWeatherAttrsIfNoData.setPressure(1015.6);
		defaultWeatherAttrsIfNoData.setHumidity(69);
		return defaultWeatherAttrsIfNoData;
		
	}
	
	private static WeatherAttributes getDefaultAttributes(List<WeatherAttributes> weatherAttrs, String date){
		WeatherAttributes defaultWeatherAttrsIfNoData = new WeatherAttributes();
		/**
		 * If values are not available in the previous year, then use the year 2016 values by default for all forecast.
		 
		 * Take the average for the given month in 2016, 
		 * and get some random value between the mean and standard deviation for the values in the month
		 * */
		//assign any random value between mean of all pressumre/humidty in the month(year 2016) and (mean + standard deviation)
		//
		double[] pressure = weatherAttrs.stream().filter( p -> {
			return p.getDate().startsWith("2016-"+date.split("-")[1]);
		}).map(p -> p.getPressure()).mapToDouble(d -> d).toArray();
		
		double[] humidity = weatherAttrs.stream().filter( p -> {
			return p.getDate().startsWith("2016-" + date.split("-")[1]);
		}).map(p -> p.getHumidity()).mapToDouble(d -> d).toArray();
		Statistics pressureStats = new Statistics(pressure);
		double pressureMean = pressureStats.getMean();
		double pressureSD = pressureStats.getStdDev();
		
		
		//mean of pressure < pressure <mean + standard deviation
		//mean of humidity < humidity <mean + standard deviation
		Statistics humidityStats = new Statistics(humidity);
		double humidityMean = pressureStats.getMean();
		double humiditySD = pressureStats.getStdDev();
		if(Double.isNaN(pressureMean)||Double.isNaN(pressureSD)){
			return getDefaultAttributesHardCoded();
		}
		defaultWeatherAttrsIfNoData.setPressure(getRandomDoubleInRage(pressureMean,pressureMean + pressureSD));
		defaultWeatherAttrsIfNoData.setHumidity(getRandomDoubleInRage(humidityMean,humidityMean + humiditySD));
		
		return defaultWeatherAttrsIfNoData;
		
	}
	private static WeatherAttributes getVal(final List<WeatherAttributes> listWeather, String date){
			/*
			 * Using the same value as last year for now,
			 * 
			 * */
			return listWeather.stream()
					.filter(p -> {
						return cleanseDate(p.getDate()).equals(date);
						
					}).
				    findFirst().orElse(getDefaultAttributes(listWeather,date));
			
	}
	
	private static String cleanseDate(String d){
		String[] splitDate = Optional.ofNullable(d.split("-")).orElse(null);
		if(splitDate!=null && splitDate.length == 3){
			if(splitDate[0].length()!=4){}
			if(splitDate[1].length()==1 && Integer.parseInt(splitDate[1]) < 10){
				splitDate[1] = "0"+splitDate[1];
			}
			if(splitDate[2].length()==1 && Integer.parseInt(splitDate[2]) < 10){
				splitDate[2] = "0"+splitDate[2];
			}
			return String.join("-",splitDate);
		}
		return "";
		
	}
	private String getStringDate(Date date){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String reportDate = df.format(date);
		return reportDate;
	}
	
	private String getTemperature(RegressionEstimateAttributes reg, WeatherAttributes temp){
		double reg1 = reg.getReg1();
		double reg2 = reg.getReg2();
		double reg3 = reg.getReg3();
		DecimalFormat df = new DecimalFormat("#.##");
		temp.setTemperature(reg1 + reg2 * temp.getHumidity() + reg3*temp.getPressure());
		return df.format(temp.getTemperature());
	}
	
	private static Date getPreviousYear(int offset){
		Calendar cal = Calendar.getInstance();
		
		cal.add(Calendar.YEAR, -1);
		cal.add(Calendar.DATE, offset);
		return cal.getTime();
		
	}
	
	private static Date getDateOffset(int offset){
		Calendar cal = Calendar.getInstance();
		Date today = cal.getTime();
		
		cal.add(Calendar.DATE, offset);
		return cal.getTime();
		
	}
	
	private static String getCondition(WeatherAttributes temp){
		if(temp.getRainfall() > 20 || temp.getHumidity() > AppConstants.HUMIDITY_LEVEL){
			return Condition.RAIN.toString();
		}
		if(temp.getTemperature() < AppConstants.SNOW_LEVEL ) {
			return Condition.SNOW.toString();
		}
		return Condition.SUNNY.toString();
	}
	@Override
	public void simulateForGame(final MasterWeather mw) throws IOException {

		Map<String,String> location = DataUtil.getLocation();
		
		mw.getCityData().forEach((k,v) -> {
			IntStream.range(0, AppConstants.NUMBER_OF_DAYS)
			  .forEach(idx ->
			  {
				   ToySimulator toySim  = new ToySimulator();
				   toySim.setLocation(location.get(k));
				   toySim.setCity(k);
				   String d = getStringDate(getPreviousYear(idx));
					WeatherAttributes temp;
					try{
						temp = getVal(v, d);
					}
					catch(Exception e){
						return;
					}
					toySim.setHumidity(Double.valueOf(temp.getHumidity()).toString());
					toySim.setPressure(Double.valueOf(temp.getPressure()).toString());
					toySim.setDate(getStringDate(getDateOffset(idx)));
					toySim.setCondition(getCondition(temp));
					toySim.setTemperature(getTemperature(mw.getCityRegParams().get(k),temp));
					System.out.println(toySim.toString());
			  }
			  )
			;
			
		});
		
		
		
		
	}

}
