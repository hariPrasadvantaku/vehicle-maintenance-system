package com.hari.maintenance.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.hari.maintenance.model.Vehicle;
import com.hari.maintenance.service.VehicleService;

@RestController
@RequestMapping("/vehicle")
@CrossOrigin(origins = "http://localhost:3000")

public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping
    public Vehicle addVehicle(Authentication auth,
                              @RequestBody Vehicle vehicle) {
        return vehicleService.add(vehicle, auth.getName());
    }

    @GetMapping
    public List<Vehicle> getAllVehicles(Authentication auth) {
        return vehicleService.getAllVehicles(auth.getName());
    }

    @PutMapping("/{vehicleId}")
    public Vehicle updateVehicle(Authentication auth,
                                 @PathVariable long vehicleId,
                                 @RequestBody Vehicle vehicle) {
        return vehicleService.updateVehicle(auth.getName(), vehicleId, vehicle);
    }
    
    @GetMapping("/{vehicleId}")
    public Vehicle getVehicle(Authentication auth,
                              @PathVariable long vehicleId) {
        return vehicleService.getVehicleById(auth.getName(), vehicleId);
    }
    
    @GetMapping("/search")
    public List<Vehicle> searchVehicle(Authentication auth,
                                        @RequestParam String keyword) {
        return vehicleService.searchVehicle(auth.getName(), keyword);
    }
    
    @GetMapping("/insurance-due")
    public List<Vehicle> insuranceDue(Authentication auth) {
        return vehicleService.getUpcomingInsurance(auth.getName());
    }

    @DeleteMapping("/{vehicleId}")
    public void deleteVehicle(Authentication auth,
                              @PathVariable long vehicleId) {
        vehicleService.deleteVehicle(vehicleId, auth.getName());
    }
}
