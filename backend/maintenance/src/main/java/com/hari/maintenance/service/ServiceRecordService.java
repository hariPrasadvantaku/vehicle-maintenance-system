package com.hari.maintenance.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hari.maintenance.model.ServiceRecord;
import com.hari.maintenance.model.Vehicle;
import com.hari.maintenance.repo.ServiceRepo;
import com.hari.maintenance.repo.VehicleRepo;

@Service
public class ServiceRecordService {

	private ServiceRepo serviceRepo;
	private VehicleRepo vehicleRepo;

	public ServiceRecordService(ServiceRepo serviceRepo, VehicleRepo vehicleRepo) {
		this.serviceRepo = serviceRepo;
		this.vehicleRepo = vehicleRepo;
	}

	public ServiceRecord addService(long vehicleId, String email, ServiceRecord record) {

		Vehicle vehicle = vehicleRepo.findById(vehicleId).orElseThrow(() -> new RuntimeException("Vehicle not found"));

		if (vehicle.getUser() == null || !vehicle.getUser().getEmail().equals(email)) {
			throw new RuntimeException("Unauthorized");
		}

		record.setVehicle(vehicle);
		return serviceRepo.save(record);
	}

	public List<ServiceRecord> getServicesByVehicle(long vehicleId, String email) {

		Vehicle vehicle = vehicleRepo.findById(vehicleId).orElseThrow(() -> new RuntimeException("Vehicle not found"));

		if (vehicle.getUser() == null || !vehicle.getUser().getEmail().equals(email)) {
			throw new RuntimeException("Unauthorized");
		}

		return serviceRepo.findByVehicleId(vehicleId);
	}

	public ServiceRecord getServiceById(long serviceId, String email) {

		ServiceRecord record = serviceRepo.findById(serviceId)
				.orElseThrow(() -> new RuntimeException("Service not found"));

		if (record.getVehicle().getUser() == null || !record.getVehicle().getUser().getEmail().equals(email)) {
			throw new RuntimeException("Unauthorized");
		}

		return record;
	}

	public ServiceRecord updateService(long serviceId, ServiceRecord newData, String email) {

		ServiceRecord existing = serviceRepo.findById(serviceId)
				.orElseThrow(() -> new RuntimeException("Service not found"));

		if (existing.getVehicle().getUser() == null || !existing.getVehicle().getUser().getEmail().equals(email)) {
			throw new RuntimeException("Unauthorized");
		}

		existing.setDescription(newData.getDescription());
		existing.setCost(newData.getCost());
		existing.setNextServiceDate(newData.getNextServiceDate());
		existing.setServiceDate(newData.getServiceDate());
		existing.setServiceType(newData.getServiceType());

		return serviceRepo.save(existing); 
	}

	public void deleteService(long serviceId, String email) {

		ServiceRecord record = serviceRepo.findById(serviceId)
				.orElseThrow(() -> new RuntimeException("Service not found"));

		if (record.getVehicle().getUser() == null || !record.getVehicle().getUser().getEmail().equals(email)) {
			throw new RuntimeException("Unauthorized");
		}

		serviceRepo.delete(record);
	}

	public List<ServiceRecord> searchService(String email, String keyword) {

		return serviceRepo.findByServiceTypeContainingAndVehicleUserEmail(keyword, email);
	}

	public List<ServiceRecord> upcomingServices(String email) {

		LocalDate future = LocalDate.now().plusDays(15);

		return serviceRepo.findByNextServiceDateBeforeAndVehicleUserEmail(future, email);
	}
}
