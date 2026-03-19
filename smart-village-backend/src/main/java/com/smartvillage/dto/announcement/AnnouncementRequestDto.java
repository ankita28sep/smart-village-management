package com.smartvillage.dto.announcement;

import com.smartvillage.enums.AnnouncementType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AnnouncementRequestDto {
	@NotBlank(message = "Title is required")
	@Size(max = 100, message = "Title must be less than 100 characters")
	private String title;
	@NotBlank(message = "Content is required")
	private String content;
	@NotNull(message = "type is required")
	private AnnouncementType type;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public AnnouncementType getType() {
		return type;
	}
	public void setType(AnnouncementType type) {
		this.type = type;
	}
	
	
	

}
