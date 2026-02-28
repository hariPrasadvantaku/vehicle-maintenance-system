package com.hari.maintenance.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class VehicleDTO {
	private Long id;
	private String vehicleName;
	private String make;
	private String model;
	private String registrationNo;
	private String fuelType;
	private LocalDate purchaseDate;
	private LocalDate insuranceExpiryDate;
}
