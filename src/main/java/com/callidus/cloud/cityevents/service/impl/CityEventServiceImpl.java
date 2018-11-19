package com.callidus.cloud.cityevents.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.callidus.cloud.cityevents.model.CityEvent;
import com.callidus.cloud.cityevents.service.CityEventService;
import com.callidus.cloud.cityevents.util.RestClientUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CityEventServiceImpl implements CityEventService {

	private RestTemplate restTemplate;
	private RestClientUtil restClientUtil;
	private ObjectMapper mapper;
	
	public CityEventServiceImpl(RestTemplate restTemplate, RestClientUtil restClientUtil, ObjectMapper mapper) {
		this.restTemplate = restTemplate;
		this.restClientUtil = restClientUtil;
		this.mapper = mapper;
	}

	@Override
	public List<CityEvent> getCityEvents(double cityLat, double cityLon) {
		
		ResponseEntity<String> response = restTemplate.getForEntity(restClientUtil.getCityEvents(cityLon, cityLat), String.class);
		
		List<CityEvent> cityEventList = new ArrayList<>();
		
		try {
			cityEventList = new ArrayList<>(Arrays.asList(mapper.readValue(response.getBody(), CityEvent[].class)));
		} catch (Exception e) {
			throw new RuntimeException("Failed to load events!");
		}
		
		return cityEventList;
	}

	@Override
	public void displayCityEvents(List<CityEvent> cityEvents, String cityName) {
		
		System.out.println();
		System.out.println("List of events in " + cityName + " :");
		System.out.println();
			
		for(int i = 0; i < cityEvents.size(); i++) {
			System.out.println("***************");
			System.out.println("Event name: " + cityEvents.get(i).getName());
			System.out.println("Local date: " + cityEvents.get(i).getLocalDate());
			System.out.println("Local time: " + cityEvents.get(i).getLocalTime());
			System.out.println("Event description: " + cityEvents.get(i).getDescription());
			System.out.println("Address: " + cityEvents.get(i).getAddress1());
			System.out.println("Link: " + cityEvents.get(i).getLink());
			System.out.println("Visibility: " + cityEvents.get(i).getVisibility());
			System.out.println("***************" + "\n");
		}
	}
}
