package com.hari.maintenance.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hari.maintenance.model.User;
import com.hari.maintenance.model.Vehicle;
import com.hari.maintenance.repo.UserRepo;
import com.hari.maintenance.repo.VehicleRepo;

@Service
public class VehicleService {

    private VehicleRepo vehicleRepo;
    private UserRepo userRepo;

    public VehicleService(VehicleRepo vehicleRepo, UserRepo userRepo) {
        this.vehicleRepo = vehicleRepo;
        this.userRepo = userRepo;
    }


    public Vehicle add(Vehicle vehicle, String email) {

        User user = userRepo.findByEmail(email);
        if (user == null) throw new RuntimeException("User not found");

        vehicle.setUser(user);
        return vehicleRepo.save(vehicle);
    }

    public List<Vehicle> getAllVehicles(String email) {

        User user = userRepo.findByEmail(email);
        if (user == null) throw new RuntimeException("User not found");

        return vehicleRepo.findByUserId(user.getId());
    }

    public Vehicle updateVehicle(String email, long vehicleId, Vehicle vehicle) {

        Vehicle existingVehicle = vehicleRepo.findById(vehicleId)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        if (existingVehicle.getUser() == null ||
            !existingVehicle.getUser().getEmail().equals(email)) {
            throw new RuntimeException("Unauthorized");
        }

        existingVehicle.setVehicleName(vehicle.getVehicleName());
        existingVehicle.setModel(vehicle.getModel());
        existingVehicle.setRegistrationNo(vehicle.getRegistrationNo());
        existingVehicle.setFuelType(vehicle.getFuelType());
        existingVehicle.setInsuranceExpiryDate(vehicle.getInsuranceExpiryDate());

        return vehicleRepo.save(existingVehicle);
    }
    
    public Vehicle getVehicleById(String email, long vehicleId) {

        Vehicle vehicle = vehicleRepo.findById(vehicleId)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        if (vehicle.getUser() == null ||
            !vehicle.getUser().getEmail().equals(email)) {
            throw new RuntimeException("Unauthorized");
        }

        return vehicle;
    }
    
    public List<Vehicle> searchVehicle(String email, String keyword) {

        User user = userRepo.findByEmail(email);
        if (user == null) throw new RuntimeException("User not found");

        return vehicleRepo.findByVehicleNameContainingAndUserId(
                keyword, user.getId());
    }
    
    public List<Vehicle> getUpcomingInsurance(String email) {

        User user = userRepo.findByEmail(email);
        if (user == null) throw new RuntimeException("User not found");

        Date futureDate = new Date(System.currentTimeMillis() + (15L * 24 * 60 * 60 * 1000));

        return vehicleRepo.findByInsuranceExpiryDateBeforeAndUserId(
                futureDate, user.getId());
    }
    
    public void deleteVehicle(long vehicleId, String email) {

        Vehicle vehicle = vehicleRepo.findById(vehicleId)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        if (vehicle.getUser() == null ||
            !vehicle.getUser().getEmail().equals(email)) {
            throw new RuntimeException("Unauthorized");
        }

        vehicleRepo.delete(vehicle);
    }
}
