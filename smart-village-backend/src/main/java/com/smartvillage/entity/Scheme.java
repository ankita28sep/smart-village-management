package com.smartvillage.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "schemes")
public class Scheme {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(nullable = false, unique = true, length = 100)
	private String name;
	@Column(nullable = false, columnDefinition = "TEXT")
	private String description;
	@Embedded
	private Eligibility eligibility;
	@Column(nullable = false)
	private LocalDate startDate;
	@Column(nullable = false)
	private LocalDate endDate;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "posted_by", nullable = false)
	@JsonIgnore
	private User postedBy;
	@Column(nullable = false)
	private boolean active;

	public Scheme() {
		super();
	}

	public Scheme(String name, String description, Eligibility eligibility, LocalDate startDate, LocalDate endDate,
			User postedBy, boolean active) {
		super();
		this.name = name;
		this.description = description;
		this.eligibility = eligibility;
		this.startDate = startDate;
		this.endDate = endDate;
		this.postedBy = postedBy;
		this.active = active;
	}

	public Scheme(long id, String name, String description, Eligibility eligibility, LocalDate startDate,
			LocalDate endDate, User postedBy, boolean active) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.eligibility = eligibility;
		this.startDate = startDate;
		this.endDate = endDate;
		this.postedBy = postedBy;
		this.active = active=true;
	}
	@PrePersist
	public void onCreate() {
	    if (this.startDate == null) {
	        this.startDate = LocalDate.now();
	    }
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Eligibility getEligibility() {
		return eligibility;
	}

	public void setEligibility(Eligibility eligibility) {
		this.eligibility = eligibility;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public User getPostedBy() {
		return postedBy;
	}

	public void setPostedBy(User postedBy) {
		this.postedBy = postedBy;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "Scheme [id=" + id + ", name=" + name + ", active=" + active + "]";
	}

}
