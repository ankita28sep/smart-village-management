package com.smartvillage.dto.complaint;

import com.smartvillage.enums.ComplaintStatus;

public class ComplaintResponseDto {

    private Long id;
    private String title;
    private String description;
    private ComplaintStatus status;

    private Long citizenId;
    private String citizenName;

    private Long handledById;
    private String handledByName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Long getCitizenId() {
        return citizenId;
    }

    public void setCitizenId(Long citizenId) {
        this.citizenId = citizenId;
    }

    public String getCitizenName() {
        return citizenName;
    }

    public void setCitizenName(String citizenName) {
        this.citizenName = citizenName;
    }

    public Long getHandledById() {
        return handledById;
    }

    public void setHandledById(Long handledById) {
        this.handledById = handledById;
    }

    public String getHandledByName() {
        return handledByName;
    }

    public void setHandledByName(String handledByName) {
        this.handledByName = handledByName;
    }
}