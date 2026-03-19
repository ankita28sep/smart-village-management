package com.smartvillage.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.smartvillage.enums.AnnouncementStatus;
import com.smartvillage.enums.AnnouncementType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "announcements")
public class Announcement {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "title", nullable = false, length = 100, unique = true)
	private String title;
	@Column(name = "content", nullable = false, columnDefinition = "TEXT")
	private String content;
	@Enumerated(EnumType.STRING)
	@Column(name="type",nullable=false)
	private AnnouncementType type;
	@Enumerated(EnumType.STRING)
	@Column(name="status",nullable=false)
	private AnnouncementStatus status;
	@Column(name = "posted_at", nullable = false, updatable = false)
	private LocalDateTime postedAt;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "posted_by", nullable = false)
	@JsonIgnore
	private User postedBy;

	public Announcement() {
		super();
	}

	public Announcement(String title, String content, User postedBy) {
		super();
		this.title = title;
		this.content = content;
		this.postedBy = postedBy;
	}

	public Announcement(long id, String title, String content, User postedBy) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.postedBy = postedBy;
	}
	
	@PrePersist
	public void onCreate() {
	    this.postedAt = LocalDateTime.now();
	    this.status = AnnouncementStatus.ACTIVE; // Set default status to ACTIVE when creating a new announcement
	}

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

	public AnnouncementType getType() {
		return type;
	}

	public void setType(AnnouncementType type) {
		this.type = type;
	}
	

	public AnnouncementStatus getStatus() {
		return status;
	}

	public void setStatus(AnnouncementStatus status) {
		this.status = status;
	}

	public LocalDateTime getPostedAt() {
		return postedAt;
	}
	public void setPostedAt(LocalDateTime postedAt) {
		this.postedAt = postedAt;
	}

	public User getPostedBy() {
		return postedBy;
	}

	public void setPostedBy(User postedBy) {
		this.postedBy = postedBy;
	}

	@Override
	public String toString() {
		return "Announcement [id=" + id + ", title=" + title + ", content=" + content + "]";
	}

}
