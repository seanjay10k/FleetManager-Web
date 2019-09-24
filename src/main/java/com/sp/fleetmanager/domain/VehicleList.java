package com.sp.fleetmanager.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="vehicles")
public class VehicleList {
	private List<Vehicle> vList;
	public VehicleList() {}
	public VehicleList(List<Vehicle> vList) {
		this.vList=vList;
	}
	
	@XmlElement(name="vehicle")
	public List<Vehicle> getvList() {
		return vList;
	}
	public void setvList(List<Vehicle> vList) {
		this.vList = vList;
	}
	
	
}
