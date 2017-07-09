package com.commbank.weathersim.data;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import com.commbank.weathersim.constants.AppConstants;




public class DataUtil {
	private static Properties properties = new Properties();
	private static List<String> YEAR_MONTH;
	private static Set<String> ID_CITIES = new HashSet<String>();
	private static String algoChoice="";
	
	private boolean mainColumn = false;
	private static Map<String,String> locationMap=null;
	
	public static Map<String,String> getLocation() throws IOException{
		if(locationMap == null){
			locationMap = FileReadUtil.readLocationFile();
		}
		return locationMap;
	}
	
	private static void initCitiesAndDates() throws FileNotFoundException, IOException{
		
		YEAR_MONTH = Arrays.asList(new String[]{"201606","201607","201608","201609","201610","201611","201612",
				  "201701","201702","201703","201704","201705","201706","201707"});
		
		properties.load(new FileInputStream(AppConstants.RESOURCE_FOLDER + "city-list.properties"));
		
		ID_CITIES = properties.stringPropertyNames();
		
		
	}
	
	private HttpURLConnection prepareCon(String url) throws IOException{
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		
		// optional default is GET
		con.setRequestMethod("GET");
	
		//add request header
		con.setRequestProperty("User-Agent", AppConstants.USER_AGENT);
		return con;
		
	}
	
	private StringBuffer getDataFromBOM() throws FileNotFoundException, IOException{
		try{
				ID_CITIES.forEach(cityId -> {
									try{
										if(new File(AppConstants.RESOURCE_FOLDER_DATA+ cityId + ".txt").exists()){
											return;
										}
										StringBuffer response = new StringBuffer();
										this.mainColumn = false;
										
										YEAR_MONTH.forEach(date -> {
											try{
										
												String url = AppConstants.BOM_URL +date+"/text/"+properties.getProperty(cityId)+"."+date+".csv";
												HttpURLConnection con = prepareCon(url);
																								
												BufferedReader in = new BufferedReader(
												        new InputStreamReader(con.getInputStream()));
												String inputLine;
												
												boolean headerReached = false;
												while ((inputLine = in.readLine()) != null) {
													if(!headerReached && inputLine.toLowerCase().contains("date")){
														headerReached = true;
														
													}
													if(headerReached){
														if(!this.mainColumn){
															this.mainColumn = true;
															response.append(inputLine+"\n");
														}
														else{
															if(!inputLine.toLowerCase().contains("date")) {					
																response.append(inputLine+"\n");}
															}
														
													}
													
													
													
													
												}
												Thread.sleep(3000);
												System.out.println("Response is" + response);
												in.close();
											}
											catch(Exception e){
												
											}
											
										});
										pushDataToFile(response,cityId);
									}
									catch(Exception e) {
										
									}
							
								});
		}
		catch(Exception e){
			
		}
		
		return null;
		
	}
	private void pushDataToFile(StringBuffer response, String fileName) throws IOException{
		BufferedWriter bwr = new BufferedWriter(new FileWriter(new File(AppConstants.RESOURCE_FOLDER_DATA + fileName + ".txt")));
	    
	    bwr.write(response.toString());
	   
	    bwr.flush();
	   
	    bwr.close();
		
	}
	public void populateDataFromBOMApi() throws IOException, InterruptedException{
		try{
			initCitiesAndDates();
			this.getDataFromBOM();
		}
		catch(Exception e){
			throw e;
		}
	} 
}
