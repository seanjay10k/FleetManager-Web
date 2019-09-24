package com.sp.fleetmanager.controller.data;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sp.fleetmanager.domain.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

	Vehicle findByName(String name);

}
