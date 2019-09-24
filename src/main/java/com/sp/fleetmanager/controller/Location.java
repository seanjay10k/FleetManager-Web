package com.sp.fleetmanager.controller;

import java.util.Date;

public class Location {
	private String lat;
	private String longitude;
	private Date timestamp;
	
	public String toString() {
		return this.lat+" , "+this.longitude+" , "+this.timestamp;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
	
}
