package com.hari.maintenance.dto;

import com.hari.maintenance.model.Vehicle;

public class VehicleMapper {

    public static VehicleDTO toDTO(Vehicle v) {
        VehicleDTO dto = new VehicleDTO();

        dto.setId(v.getId());
        dto.setVehicleName(v.getVehicleName());
        dto.setMake(v.getMake());
        dto.setModel(v.getModel());
        dto.setRegistrationNo(v.getRegistrationNo());
        dto.setFuelType(v.getFuelType());
        dto.setPurchaseDate(v.getPurchaseDate());
        dto.setInsuranceExpiryDate(v.getInsuranceExpiryDate());

        return dto;
    }
}