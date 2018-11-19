package com.callidus.cloud.cityevents.model;

public class City {

	private double lat;
	private double lon;
	private String city;
	
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLon() {
		return lon;
	}
	public void setLon(double lon) {
		this.lon = lon;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	@Override
	public String toString() {
		return "City [lat=" + lat + ", lon=" + lon + ", city=" + city + "]";
	}
	
}
