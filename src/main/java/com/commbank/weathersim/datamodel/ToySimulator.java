package com.commbank.weathersim.datamodel;

public class ToySimulator {
	String city;
	String location;
	String date;
	String condition;
	String temperature;
	String pressure;
	String humidity;
	

	@Override
	public String toString() {
		// TODO Auto-generated method stub
	
		return this.city+"|"+this.location+"|"+this.date
				+"|"+this.condition+"|"+this.temperature+"|"+this.pressure+"|"+this.humidity;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public String getTemperature() {
		return temperature;
	}
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	public String getPressure() {
		return pressure;
	}
	public void setPressure(String pressure) {
		this.pressure = pressure;
	}
	public String getHumidity() {
		return humidity;
	}
	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}
	

}
