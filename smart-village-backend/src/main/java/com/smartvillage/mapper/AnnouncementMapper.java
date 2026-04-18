package com.smartvillage.mapper;

import com.smartvillage.dto.announcement.AnnouncementRequestDto;
import com.smartvillage.dto.announcement.AnnouncementResponseDto;
import com.smartvillage.dto.announcement.AnnouncementUpdateDto;
import com.smartvillage.entity.Announcement;
import com.smartvillage.entity.User;

public class AnnouncementMapper {
	// TO CONVERT THE REQUESTDTO TO ENTITY
	public static Announcement toEntity(AnnouncementRequestDto dto, User user) {
		Announcement announcement = new Announcement();
		announcement.setTitle(dto.getTitle());
		announcement.setContent(dto.getContent());
		announcement.setType(dto.getType());
		announcement.setPostedBy(user);
	
		

		return announcement;
	}
	// TO CONVERT THE ENTITY TO RESPONSEDTO
	public static AnnouncementResponseDto toDto(Announcement announcement) {
		AnnouncementResponseDto dto = new AnnouncementResponseDto();
		dto.setId(announcement.getId());
		dto.setTitle(announcement.getTitle());
		dto.setContent(announcement.getContent());
		dto.setPostedAt(announcement.getPostedAt());
		if(announcement.getPostedBy()!=null) {
			dto.setPostedByName(announcement.getPostedBy().getName());
			dto.setPostedById(announcement.getPostedBy().getId());
		dto.setStatus(announcement.getStatus());
		dto.setType(announcement.getType());
		
		}
		
		return dto;
	}
	// TO UPDATE THE ENTITY VALUSES BY THE REQUEST DTO VALUES
	 public static void updateEntity(Announcement a, AnnouncementUpdateDto dto) {
	        if (dto.getTitle() != null) {
	            a.setTitle(dto.getTitle());
	        }
	        if (dto.getContent() != null) {
	            a.setContent(dto.getContent());
	        }
	}

}
