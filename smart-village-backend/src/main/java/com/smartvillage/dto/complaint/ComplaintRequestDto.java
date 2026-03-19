package com.smartvillage.dto.complaint;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ComplaintRequestDto {
	@NotBlank(message = "Title is required")
	private String title;
	@NotBlank(message = "Description is required")
	private String description;
	@NotNull(message = "Citizen ID is required")
	private Long citizenId;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getCitizenId() {
		return citizenId;
	}

	public void setCitizenId(Long citizenId) {
		this.citizenId = citizenId;
	}

}
