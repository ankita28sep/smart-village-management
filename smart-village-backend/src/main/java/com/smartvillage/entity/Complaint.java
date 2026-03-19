package com.smartvillage.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.smartvillage.enums.ComplaintStatus;

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
@Table(name = "complaints")
public class Complaint {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(nullable = false)
	private String title;
	@Column(nullable = false,columnDefinition = "TEXT")
	private String description;
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private ComplaintStatus status;
	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDate createdAt;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "citizen_id", nullable = false)
	@JsonIgnore
	private User citizen;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "handled_by")
	@JsonIgnore
	private User handledBy;

	public Complaint() {
		super();
	}

	public Complaint(String title, String description, ComplaintStatus status, User citizen,
			User handledBy) {
		super();
		this.title = title;
		this.description = description;
		this.status = status;
		this.citizen = citizen;
		this.handledBy = handledBy;
	}

	public Complaint(long id, String title, String description, ComplaintStatus status,
			User citizen, User handledBy) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.status = status;
		this.citizen = citizen;
		this.handledBy = handledBy;
	}
	@PrePersist
	public void onCreate() {
	    this.createdAt = LocalDate.now();
	    if (this.status == null) {
	        this.status = ComplaintStatus.PENDING;
	    }
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

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public User getCitizen() {
		return citizen;
	}

	public void setCitizen(User citizen) {
		this.citizen = citizen;
	}

	public User getHandledBy() {
		return handledBy;
	}

	public void setHandledBy(User handeledBy) {
		this.handledBy = handeledBy;
	}

	@Override
	public String toString() {
		return "Complaint [id=" + id + ", title=" + title + ", status=" + status + "]";
	}

}
