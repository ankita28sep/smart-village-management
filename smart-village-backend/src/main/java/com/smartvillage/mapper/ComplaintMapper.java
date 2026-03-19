package com.smartvillage.mapper;

import com.smartvillage.dto.complaint.ComplaintRequestDto;
import com.smartvillage.dto.complaint.ComplaintResponseDto;
import com.smartvillage.dto.complaint.ComplaintUpdateDto;
import com.smartvillage.entity.Complaint;
import com.smartvillage.entity.User;

public class ComplaintMapper {
	
	// TO CONVERT THE REQUESTDTO TO ENTITY
	public static Complaint toEntity(ComplaintRequestDto dto, User citizen) {

		Complaint c = new Complaint();
		c.setTitle(dto.getTitle());
		c.setDescription(dto.getDescription());
		c.setCitizen(citizen);
		return c;
	}

	// TO CONVERT THE ENTITY TO RESPONSEDTO
	public static ComplaintResponseDto toDto(Complaint c) {
		ComplaintResponseDto dto = new ComplaintResponseDto();
		dto.setId(c.getId());
		dto.setTitle(c.getTitle());
		dto.setDescription(c.getDescription());
		dto.setStatus(c.getStatus());
		
		// SET CITITZEN NAME
		if (c.getCitizen() != null) {
			dto.setCitizenName(c.getCitizen().getName());
		}
		// SET HANDELBBY NAME
		if (c.getHandledBy() != null) {
			dto.setHandledByName(c.getHandledBy().getName());
		}
		return dto;

	}

	// TO UPDATE THE ENTITY VALUSES BY THE REQUEST DTO VALUES
	public static void updateEntity(Complaint c, ComplaintUpdateDto dto) {
		c.setTitle(dto.getTitle());
		c.setDescription(dto.getDescription());
	}

}
