package com.sp.fleetmanager.domain;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Vehicle {
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	private long id;
	private String name;
	private int odometer;
	private String status;
	private String lat;
	private String lon;
	private Date latestPing;
	private String driverName;
	public Vehicle() { 	
	}
	

	public String toString() {
		return this.name+ ", odometer: "+ this.odometer+" , driver:"+this.driverName+" , last ping at "+latestPing+" ,from lat/long: "+lat+" , "+lon;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getOdometer() {
		return odometer;
	}


	public void setOdometer(int odometer) {
		this.odometer = odometer;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getLat() {
		return lat;
	}


	public void setLat(String lat) {
		this.lat = lat;
	}


	public String getLon() {
		return lon;
	}


	public void setLon(String lon) {
		this.lon = lon;
	}


	public Date getLatestPing() {
		return latestPing;
	}


	public void setLatestPing(Date latestPing) {
		this.latestPing = latestPing;
	}


	public String getDriverName() {
		return driverName;
	}


	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	
 
	
}
