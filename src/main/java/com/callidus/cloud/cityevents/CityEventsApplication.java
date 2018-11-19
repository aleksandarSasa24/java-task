package com.callidus.cloud.cityevents;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.callidus.cloud.cityevents.model.City;
import com.callidus.cloud.cityevents.service.CityService;
import com.callidus.cloud.cityevents.service.UserInputService;

@SpringBootApplication
public class CityEventsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CityEventsApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner run(CityService cityService, UserInputService userInputService) 
			throws Exception {
		
		return args -> {
			
			List<City> cities = cityService.getCities();
			
			if (cities.size() > 0) {
				
				cityService.displayCities(cities);
				userInputService.getUserInput(cities);
			}
		};
	}
}
