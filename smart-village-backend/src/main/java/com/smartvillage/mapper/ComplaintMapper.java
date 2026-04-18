package com.smartvillage.mapper;

import com.smartvillage.dto.complaint.ComplaintRequestDto;
import com.smartvillage.dto.complaint.ComplaintResponseDto;
import com.smartvillage.dto.complaint.ComplaintUpdateDto;
import com.smartvillage.entity.Complaint;
import com.smartvillage.entity.User;

public class ComplaintMapper {

    // REQUEST DTO → ENTITY
    public static Complaint toEntity(ComplaintRequestDto dto, User citizen) {

        Complaint c = new Complaint();
        c.setTitle(dto.getTitle());
        c.setDescription(dto.getDescription());
        c.setCitizen(citizen);

        return c;
    }

    // ENTITY → RESPONSE DTO
    public static ComplaintResponseDto toDto(Complaint c) {

        ComplaintResponseDto dto = new ComplaintResponseDto();

        dto.setId(c.getId());
        dto.setTitle(c.getTitle());
        dto.setDescription(c.getDescription());
        dto.setStatus(c.getStatus());

        // Citizen info
        if (c.getCitizen() != null) {
            dto.setCitizenName(c.getCitizen().getName());
        }

        // Handler info
        if (c.getHandledBy() != null) {
            dto.setHandledByName(c.getHandledBy().getName());
        }

        return dto;
    }

    // UPDATE ENTITY (partial update)
    public static void updateEntity(Complaint c, ComplaintUpdateDto dto) {

        if (dto.getTitle() != null) {
            c.setTitle(dto.getTitle());
        }

        if (dto.getDescription() != null) {
            c.setDescription(dto.getDescription());
        }
    }
}