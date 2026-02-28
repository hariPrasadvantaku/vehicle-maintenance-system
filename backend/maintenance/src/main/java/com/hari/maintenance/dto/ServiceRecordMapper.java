package com.hari.maintenance.dto;

import com.hari.maintenance.model.ServiceRecord;

public class ServiceRecordMapper {

	public static ServiceRecordDTO toDTO(ServiceRecord record) {
		ServiceRecordDTO dto = new ServiceRecordDTO();

		dto.setId(record.getId());
		dto.setServiceType(record.getServiceType());
		dto.setServiceDate(record.getServiceDate());
		dto.setNextServiceDate(record.getNextServiceDate());
		dto.setCost(record.getCost());
		dto.setDescription(record.getDescription());

		return dto;
	}

	public static ServiceRecord toEntity(ServiceRecordDTO dto) {
		ServiceRecord record = new ServiceRecord();

		record.setServiceType(dto.getServiceType());
		record.setServiceDate(dto.getServiceDate());
		record.setNextServiceDate(dto.getNextServiceDate());
		record.setCost(dto.getCost());
		record.setDescription(dto.getDescription());

		return record;
	}
}