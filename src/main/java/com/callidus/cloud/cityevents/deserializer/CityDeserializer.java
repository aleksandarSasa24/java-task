package com.callidus.cloud.cityevents.deserializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.jackson.JsonComponent;

import com.callidus.cloud.cityevents.model.City;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

@JsonComponent
public class CityDeserializer extends JsonDeserializer<City[]>{

	private static final String RESULTS_NODE_NAME = "results";
	private static final String CITY_NODE_NAME = "city";
	private static final String LAT_NODE_NAME = "lat";
	private static final String LON_NODE_NAME = "lot";
	
	@Override
	public City[] deserialize(JsonParser parser, DeserializationContext deserializer)
			throws IOException, JsonProcessingException {
		
	   ObjectCodec codec = parser.getCodec();
	   JsonNode root = codec.readTree(parser);
	   JsonNode cityNodes = root.get(RESULTS_NODE_NAME);
	    
	   List<City> cities = new ArrayList<>();
	    
	   if (cityNodes.isArray()) {

            for (final JsonNode cityNode : cityNodes) {
        
        		City city = toCityEvent(cityNode);
        		cities.add(city);
            }
       } 
	    
       return cities.toArray(new City[cities.size()]);
	}
	
	private City toCityEvent(JsonNode cityNode) {
		
		JsonNode cityName = getNode(cityNode, CITY_NODE_NAME);
	    JsonNode lat = getNode(cityNode, LAT_NODE_NAME);
	    JsonNode lon = getNode(cityNode, LON_NODE_NAME);

		City city = new City();
	    
		if (cityName != null)
		city.setCity(cityName.asText());
		
		if (lat != null)
		city.setLat(lat.asDouble());
		
		if (lon != null)
		city.setLon(lon.asDouble());
  		
  		return city;
	}
	
	private JsonNode getNode(JsonNode node, String nodeName) {
		return node.get(nodeName);
	}

}
