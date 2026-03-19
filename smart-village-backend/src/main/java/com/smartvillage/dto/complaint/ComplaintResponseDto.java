package com.smartvillage.dto.complaint;

import com.smartvillage.enums.ComplaintStatus;

public class ComplaintResponseDto {
	private long id;
	private String title;
	private String description;
	private ComplaintStatus status;
	private String citizenName;
	private String handledByName;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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

	public ComplaintStatus getStatus() {
		return status;
	}

	public void setStatus(ComplaintStatus status) {
		this.status = status;
	}

	public String getCitizenName() {
		return citizenName;
	}

	public void setCitizenName(String citizenName) {
		this.citizenName = citizenName;
	}

	public String getHandledByName() {
		return handledByName;
	}

	public void setHandledByName(String handledByName) {
		this.handledByName = handledByName;
	}
	
}
