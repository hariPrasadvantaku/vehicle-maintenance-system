package com.hari.maintenance.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ServiceRecordDTO {
	private Long id;
    private String serviceType;
    private LocalDate serviceDate;
    private LocalDate nextServiceDate;
    private double cost;
    private String description;

}
