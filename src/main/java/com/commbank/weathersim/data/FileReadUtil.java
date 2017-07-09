package com.commbank.weathersim.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.apache.commons.math3.genetics.GeneticAlgorithm;

import com.commbank.weathersim.constants.AppConstants;
import com.commbank.weathersim.datamodel.WeatherAttributes;

public class FileReadUtil {
	
	private static List<File> getFiles() throws IOException{
		return Files.walk(Paths.get(AppConstants.RESOURCE_FOLDER_DATA))
                .filter(Files::isRegularFile)
                .map(Path::toFile)
                .collect(Collectors.toList());
	}
	
	private static String getParsedString(String s){
		if(s!=null && !s.isEmpty()){
			return s;
		}
		return "0";
	}
	
	private static boolean checkEmptyForSingularMatrixError(String[] s){
		return s[AppConstants.TEMPERATURE_INDEX]!=null && !s[AppConstants.TEMPERATURE_INDEX].isEmpty()
				&& s[AppConstants.HUMIDITY_INDEX]!=null && !s[AppConstants.HUMIDITY_INDEX].isEmpty()
				//&& s[AppConstants.RAINFALL_INDEX]!=null && !s[AppConstants.RAINFALL_INDEX].isEmpty()
				&& s[AppConstants.PRESSURE_INDEX]!=null && !s[AppConstants.PRESSURE_INDEX].isEmpty();
	}
	private static Consumer<String[]> getConsumer(final List<WeatherAttributes> attrs){
		Consumer<String[]> style = (String[] s) -> {
			if(!checkEmptyForSingularMatrixError(s)){
				return;
			}
			WeatherAttributes attr = new WeatherAttributes();
			attr.setDate(Optional.ofNullable(s[AppConstants.DATE_INDEX]).orElse(""));
			attr.setTemperature(Optional.ofNullable(Double.parseDouble(getParsedString(s[AppConstants.TEMPERATURE_INDEX]))).orElse(0D));
			attr.setHumidity(Optional.ofNullable(Double.parseDouble(getParsedString(s[AppConstants.HUMIDITY_INDEX]))).orElse(0D));
			attr.setRainfall(Optional.ofNullable(Double.parseDouble(getParsedString(s[AppConstants.RAINFALL_INDEX]))).orElse(0D));
			attr.setPressure(Optional.ofNullable(Double.parseDouble(getParsedString(s[AppConstants.PRESSURE_INDEX]))).orElse(0D));
			attrs.add(attr);
		};
		return style;
	}
	
	private static List<WeatherAttributes> getWeatherAttrs(File f) throws IOException{
		List<WeatherAttributes> attrs = new ArrayList<WeatherAttributes>();
		Files.lines(f.toPath(),Charset.forName("Cp1252"))
		.filter(s -> !s.toLowerCase().contains("date"))
		.map(s -> {
			return s.split(",",-1);
		})
		.forEach(getConsumer(attrs));

		return attrs;
		
	}
	/**
	 * Exposing this method
	 * @return
	 * @throws IOException
	 */
	public static Map<String,List<WeatherAttributes>> readFilesAndPopulate() throws IOException{
		Map<String,List<WeatherAttributes>> map = new HashMap<String,List<WeatherAttributes>>();
		getFiles().forEach(f -> {
			try {
				map.put(f.getName().replaceFirst("[.][^.]+$", ""), getWeatherAttrs(f));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		return map;
	}
	
	
	public static Map<String,String> readLocationFile() throws IOException{
		return Files.lines(new File(AppConstants.RESOURCE_FOLDER_LOCATION_DATA).toPath(),Charset.forName("Cp1252"))
		.map(s -> {
			String[] split = s.split(";");
			String finalString = "";
			if(split.length == 4){
				finalString = split[0] + ";"+ split[1]+","+split[2]+","+split[3]; 
			}
			return finalString;
		})
				.collect(Collectors.toMap(x -> x.split(";")[0], x -> x.split(";")[1]));
	}
	
	public static String getAlgoType(String path) throws IOException{
		try{
			//if()
			return Files.lines(Paths.get(path)).map(String::toUpperCase)
				.collect(Collectors.toList()).get(0);
		}
		catch(Exception e){
			return "APACHE";
		}
	} 
	
}
