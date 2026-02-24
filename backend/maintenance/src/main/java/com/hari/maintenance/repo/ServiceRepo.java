package com.hari.maintenance.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hari.maintenance.model.ServiceRecord;

@Repository
public interface ServiceRepo extends JpaRepository<ServiceRecord, Long> {
	List<ServiceRecord> findByVehicleId(long vehicleId);

	List<ServiceRecord> findByServiceTypeContainingAndVehicleUserEmail(String keyword, String email);

	List<ServiceRecord> findByNextServiceDateBeforeAndVehicleUserEmail(LocalDate date, String email);
}
