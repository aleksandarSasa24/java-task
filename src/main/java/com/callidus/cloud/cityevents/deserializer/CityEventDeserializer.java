package com.callidus.cloud.cityevents.deserializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.springframework.boot.jackson.JsonComponent;

import com.callidus.cloud.cityevents.model.CityEvent;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

@JsonComponent
public class CityEventDeserializer extends JsonDeserializer<CityEvent[]>{

	private static final String EVENTS_NODE_NAME = "events";
	private static final String CITY_EVENT_NODE_NAME = "name";
	private static final String LOCAL_DATE__NODE_NAME = "local_date";
	private static final String LOCAL_TIME__NODE_NAME = "local_time";
	private static final String VENUE_NODE_NAME = "venue";
	private static final String ADDRESS1_NODE_NAME = "address_1";
	private static final String DESCRIPTION_NODE_NAME = "description";
	private static final String LINK_NODE_NAME = "link";
	private static final String VISIBILITY_NODE_NAME = "visibility";
	
	@Override
	public CityEvent[] deserialize(JsonParser parser, DeserializationContext deserializer)
			throws IOException, JsonProcessingException {
		
		ObjectCodec codec = parser.getCodec();
		JsonNode root = codec.readTree(parser);
		JsonNode cityEventNodes = root.get(EVENTS_NODE_NAME);
	    
		List<CityEvent> cityEvents = new ArrayList<>();
	    
		if (cityEventNodes.isArray()) {
	
	        for (final JsonNode cityEventNode : cityEventNodes) {
	    
	    		CityEvent cityEvent = toCityEvent(cityEventNode);
	            
	    		cityEvents.add(cityEvent);
	        }
		} 
	    
		return cityEvents.toArray(new CityEvent[cityEvents.size()]);
	}
	
	private CityEvent toCityEvent(JsonNode cityEventNode) {
		
		JsonNode cityEventName = getNode(cityEventNode, CITY_EVENT_NODE_NAME);
  	    JsonNode localDate = getNode(cityEventNode, LOCAL_DATE__NODE_NAME);
  	    JsonNode localTime = getNode(cityEventNode, LOCAL_TIME__NODE_NAME);
  	    
  	    JsonNode venue = getNode(cityEventNode, VENUE_NODE_NAME);
  	    JsonNode address1 = null;
  	    if (venue != null)
  	    address1 = getNode(venue, ADDRESS1_NODE_NAME);
  	    
  	    JsonNode description = getNode(cityEventNode, DESCRIPTION_NODE_NAME);
  	    JsonNode link = getNode(cityEventNode, LINK_NODE_NAME);
  	    JsonNode visibility = getNode(cityEventNode, VISIBILITY_NODE_NAME);
  
  		CityEvent cityEvent = new CityEvent();
  	    
  		if (cityEventName != null)
  		cityEvent.setName(cityEventName.asText());
  		
  		if (localDate != null)
  		cityEvent.setLocalDate(localDate.asText());
  		
  		if (localTime != null)
  		cityEvent.setLocalTime(localTime.asText());
  		
  		if (address1 != null)
  		cityEvent.setAddress1(address1.asText());
  		
  		if (description != null)
  		cityEvent.setDescription(Jsoup.parse(description.asText()).text());
  		
  		if (link != null)
  		cityEvent.setLink(link.asText());
  		
  		if (visibility != null)
  		cityEvent.setVisibility(visibility.asText());
  		
  		return cityEvent;
	}
	
	private JsonNode getNode(JsonNode node, String nodeName) {
		return node.get(nodeName);
	}

}
