package com.hari.maintenance.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.hari.maintenance.model.ServiceRecord;
import com.hari.maintenance.service.ServiceRecordService;

@RestController
@RequestMapping("/service")
@CrossOrigin(origins = "http://localhost:3000")

public class ServiceRecordController {

	private final ServiceRecordService serviceRecordService;

	public ServiceRecordController(ServiceRecordService serviceRecordService) {
		this.serviceRecordService = serviceRecordService;
	}

	@PostMapping("/vehicle/{vehicleId}")
	public ServiceRecord addService(Authentication auth, @PathVariable long vehicleId,
			@RequestBody ServiceRecord record) {

		return serviceRecordService.addService(vehicleId, auth.getName(), record);
	}

	@GetMapping("/vehicle/{vehicleId}")
	public List<ServiceRecord> getServices(Authentication auth, @PathVariable long vehicleId) {

		return serviceRecordService.getServicesByVehicle(vehicleId, auth.getName());
	}

	@GetMapping("/{serviceId}")
	public ServiceRecord getService(Authentication auth, @PathVariable long serviceId) {

		return serviceRecordService.getServiceById(serviceId, auth.getName());
	}

	@PutMapping("/{serviceId}")
	public ServiceRecord updateService(Authentication auth, @PathVariable long serviceId,
			@RequestBody ServiceRecord record) {

		return serviceRecordService.updateService(serviceId, record, auth.getName());
	}

	@DeleteMapping("/{serviceId}")
	public void deleteService(Authentication auth, @PathVariable long serviceId) {

		serviceRecordService.deleteService(serviceId, auth.getName());
	}

	@GetMapping("/search")
	public List<ServiceRecord> searchService(Authentication auth, @RequestParam String keyword) {

		return serviceRecordService.searchService(auth.getName(), keyword);
	}

	@GetMapping("/upcoming")
	public List<ServiceRecord> upcomingServices(Authentication auth) {

		return serviceRecordService.upcomingServices(auth.getName());
	}
}
