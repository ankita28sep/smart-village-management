package com.smartvillage.dto.announcement;

import com.smartvillage.enums.AnnouncementStatus;

public class AnnouncementResponseDto {
	
	private long id;
	private String title;
	private String content;
	private String postedByName;
	private String type;
	private AnnouncementStatus status;	
	private String postedAt;	

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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public AnnouncementStatus getStatus() {
		return status;
	}
	public void setStatus(AnnouncementStatus announcementStatus) {
		this.status = announcementStatus;
	}
	public String getPostedByName() {
		return postedByName;
	}
	public void setPostedByName(String postedByName) {
		this.postedByName = postedByName;
	}
	public String getPostedAt() {
		return postedAt;
	}
	public void setPostedAt(String postedAt) {
		this.postedAt = postedAt;
	}
	

}
