package com.callidus.cloud.cityevents.model;

public class CityEvent {

	private String name;
	private String localDate;
	private String localTime;
	private String address1;
	private String description;
	private String link;
	private String visibility;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocalDate() {
		return localDate;
	}
	public void setLocalDate(String localDate) {
		this.localDate = localDate;
	}
	public String getLocalTime() {
		return localTime;
	}
	public void setLocalTime(String localTime) {
		this.localTime = localTime;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getVisibility() {
		return visibility;
	}
	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}
	
	
	@Override
	public String toString() {
		return "CityEvent [name=" + name + ", localDate=" + localDate + ", localTime=" + localTime + ", address1="
				+ address1 + ", description=" + description + ", link=" + link + ", visibility=" + visibility + "]";
	}
}
