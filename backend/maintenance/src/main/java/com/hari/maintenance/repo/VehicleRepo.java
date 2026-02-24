package com.hari.maintenance.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hari.maintenance.model.Vehicle;

public interface VehicleRepo extends JpaRepository<Vehicle,Long>{
	List<Vehicle> findByUserId(long userId);
	List<Vehicle> findByVehicleNameContainingAndUserId(String name, long userId);
	List<Vehicle> findByInsuranceExpiryDateBeforeAndUserId(Date date, long userId);
}
