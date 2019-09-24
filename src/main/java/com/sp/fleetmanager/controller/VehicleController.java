package com.sp.fleetmanager.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.sp.fleetmanager.controller.data.VehicleRepository;
import com.sp.fleetmanager.domain.Vehicle;

@Controller
@RequestMapping("/website/vehicles")
public class VehicleController 
{
	
//	@Autowired
//	private DiscoveryClient discoveryclient;//no need to use this anymore, we use loadbalancer
	
	@Autowired
	private LoadBalancerClient balancer;
	
	@Autowired
	private VehicleRepository data;

	@RequestMapping(value="/newVehicle.html",method=RequestMethod.GET)
	public ModelAndView newVehicleForm()
	{
		Vehicle v= new Vehicle();
		return new ModelAndView("newVehicle","form",v);
	} 
	
	@RequestMapping(value="/newVehicle.html",method=RequestMethod.POST)
	public String newVehicle(Vehicle v)
	{
		data.save(v);
		return "redirect:/website/vehicles/list.html";
	}
	
	@RequestMapping(value="/newVehicle.html",method=RequestMethod.DELETE)
	public String deleteVehicle(@RequestParam long v)
	{
		data.deleteById(v);
		return "redirect:/website/vehicles/list.html";
	}
	
	
	@RequestMapping(value="/list.html", method=RequestMethod.GET)	
	public ModelAndView vehicles()
	{
		List<Vehicle> vList= data.findAll();
		
		return new ModelAndView("allVehicles","vehicles",vList);
	}
	  
	@RequestMapping(value="/vehicle/{name}")
	public ModelAndView showVehicleByName(@PathVariable("name") String name) throws Exception
	{
		Vehicle vehicle = data.findByName(name);
		RestTemplate rest = new RestTemplate();
		
		//ribbon LOadBalancer will automatically choose the instance
		ServiceInstance serviceInstance =balancer.choose("fleetmanager-locationtracker");//balancer and choose the name of application in eureka
		
//		List<ServiceInstance> eurekaInstances= discoveryclient.getInstances("fleetmanager-locationtracker");
//		ServiceInstance serviceInstance = eurekaInstances.get(0);
		if(serviceInstance==null) {
			throw new Exception("Tracker crashed");
		}
		
		String mainUrl= serviceInstance.getUri().toString();
		System.out.println(mainUrl);
		Location response = rest.getForObject(mainUrl+"/vehicles/" + name, Location.class);
		System.out.println(response.toString());
		System.out.println("PORT NUM WAS::: "+serviceInstance.getPort());
		Map<String,Object> model = new HashMap<>();
		model.put("vehicle", vehicle);
		model.put("position", response);
		return new ModelAndView("vehicleInfo", "model",model);
	}
	
}
