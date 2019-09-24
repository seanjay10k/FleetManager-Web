package com.sp.fleetmanager.rest;

import java.util.List;

import org.aspectj.lang.annotation.AdviceName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sp.fleetmanager.controller.data.VehicleRepository;
import com.sp.fleetmanager.domain.Vehicle;
import com.sp.fleetmanager.domain.VehicleList;

@RestController
@Profile("dev")
public class VehicleRestController {

	@Autowired
	private VehicleRepository data;
	
	@RequestMapping("/vehicles")
	public VehicleList allVehicles() {
		List<Vehicle> vList=data.findAll();
		return new VehicleList(vList);
	}
	@RequestMapping(value="/vehicle",method=RequestMethod.DELETE)
	public VehicleList  deleteVehicle(@RequestParam long v)
	{
		data.deleteById(v);
		List<Vehicle> vList=data.findAll();
		return new VehicleList(vList);
	}
	
	@RequestMapping(value="/vehicles", method=RequestMethod.POST)
	public ResponseEntity<Vehicle> newVehicle(@RequestBody Vehicle v) {
		
		data.save(v);
		return new ResponseEntity<Vehicle>(v,HttpStatus.OK);
	}
}
