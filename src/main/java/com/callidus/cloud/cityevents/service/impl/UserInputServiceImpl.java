package com.callidus.cloud.cityevents.service.impl;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import com.callidus.cloud.cityevents.model.City;
import com.callidus.cloud.cityevents.model.CityEvent;
import com.callidus.cloud.cityevents.service.CityEventService;
import com.callidus.cloud.cityevents.service.UserInputService;

@Service
public class UserInputServiceImpl implements UserInputService{

	private CityEventService cityEventService;
	
	public UserInputServiceImpl(CityEventService cityEventService) {
		this.cityEventService = cityEventService;
	}

	@Override
	public void getUserInput(List<City> cities) {
		
		Scanner scanner = new Scanner(System.in);

		System.out.println();
		System.out.println("Enter ordinal number of city: ");
	
		try {
			
			int ordinalNumber =  scanner.nextInt();
			City city = cities.get(ordinalNumber - 1);
			List<CityEvent> cityEvents = cityEventService.getCityEvents(city.getLat(), city.getLon());
			cityEventService.displayCityEvents(cityEvents, city.getCity());

		} catch (InputMismatchException e) {
			System.out.println("You must enter ordinal number");
		} finally {
			scanner.close();
			System.exit(0);
		}
	}
}
