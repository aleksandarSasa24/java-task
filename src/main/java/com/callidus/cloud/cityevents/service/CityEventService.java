package com.callidus.cloud.cityevents.service;

import java.util.List;

import com.callidus.cloud.cityevents.model.CityEvent;

public interface CityEventService {

	List<CityEvent> getCityEvents(double cityLat, double cityLon);
	
	void displayCityEvents(List<CityEvent> cityEvents, String cityName);
}
