package com.commbank.weathersim.constants;

import java.util.Date;

public enum Location {
	SYDNEY
	,BRISBANE,
	MELBOURNE,ADELAIDE,
	CANBERRA,HOBART,
	PERTH,WOLLONGONG,
	DARWIN,GEELONG,
	TOWNSVILLE;
	
	private double latitude;
	private double longitude;
	private double altitude;
	private Date time;
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getAltitude() {
		return altitude;
	}
	public void setAltitude(double altitude) {
		this.altitude = altitude;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	
	
}
