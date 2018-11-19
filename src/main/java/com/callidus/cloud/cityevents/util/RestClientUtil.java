package com.callidus.cloud.cityevents.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RestClientUtil {

	private static final String CITY_URL_PARAMS = "country=rs&offset=0&format=json&photo-host=public&page=500&radius=50&order=size&desc=false";
	private static final String CITY_EVENT_URL_PARAMS = "photo-host=public";
	private static final String SIGNATURE_ID = "sig_id=268101703";
	private static final String CITY_SIGNATURE = "sig=e5fcc642c4767a4a44c5698c59e70110023de96f";
	private static final String CITY_EVENT_SIGNATURE = "sig=da0e4e7f28e9594c6700b74737d1b45df2c8e8a6";
	
	@Value("${meetup.url}")
	private String baseUrl;
	
	@Value("${meetup.cities}")
	private String cityUrlPath;
	
	@Value("${meetup.events}")
	private String cityEventUrlPath;
	
	public String getCitiesUrl() {
		return baseUrl + cityUrlPath + "?" + CITY_URL_PARAMS + "&" + SIGNATURE_ID + "&" + CITY_SIGNATURE;
	}
	
	public String getCityEvents(double lon, double lat) {
		return baseUrl + cityEventUrlPath + "?" + CITY_EVENT_URL_PARAMS + "&lon=" + lon + "&lat=" + lat + "&" + SIGNATURE_ID +  "&" + CITY_EVENT_SIGNATURE;
	}
}
