package com.hari.maintenance.dto;

import com.hari.maintenance.model.User;

public class UserMapper {
	public static UserDTO toDTO(User user) {
		UserDTO dto = new UserDTO();
		dto.setName(user.getName());
		dto.setEmail(user.getEmail());
		return dto;
	}
}
