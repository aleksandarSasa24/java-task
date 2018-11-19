package com.callidus.cloud.cityevents.service;

import java.util.List;

import com.callidus.cloud.cityevents.model.City;

public interface CityService {

	List<City> getCities();
	
	void displayCities(List<City> cities);
}
