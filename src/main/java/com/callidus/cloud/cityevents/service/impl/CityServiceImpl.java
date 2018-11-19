package com.callidus.cloud.cityevents.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.callidus.cloud.cityevents.model.City;
import com.callidus.cloud.cityevents.service.CityService;
import com.callidus.cloud.cityevents.util.RestClientUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CityServiceImpl implements CityService{

	private RestTemplate restTemplate;
	private RestClientUtil restClientUtil;
	private ObjectMapper mapper;
	
	public CityServiceImpl(RestTemplate restTemplate, RestClientUtil restClientUtil, ObjectMapper mapper) {
		this.restTemplate = restTemplate;
		this.restClientUtil = restClientUtil;
		this.mapper = mapper;
	}

	@Override
	public List<City> getCities() {
		
		ResponseEntity<String> response = restTemplate.getForEntity(restClientUtil.getCitiesUrl(), String.class);
		
		List<City> cityList = new ArrayList<>();
		
		try {
			cityList = new ArrayList<>(Arrays.asList(mapper.readValue(response.getBody(), City[].class)));
		} catch (Exception e) {
			throw new RuntimeException("Failed to load cities!");
		}
		
		return cityList;
	}

	@Override
	public void displayCities(List<City> cities) {
		
		System.out.println();
		System.out.println("List of cities in Serbia: ");
		System.out.println("***************" + "\n");
			
		for(int i = 0; i < cities.size(); i++) {
			System.out.println((i + 1) +  ") " + cities.get(i).getCity());
		}
		
		System.out.println("***************");
	}

}
