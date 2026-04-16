package com.smartvillage.service;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.smartvillage.entity.Complaint;
import com.smartvillage.enums.ComplaintStatus;

public interface ComplaintService {

    Complaint raiseComplaint(Complaint complaint);

    Complaint updateComplaint(long id, Complaint complaint);

    void deleteComplaint(long id);

    Complaint assignHandler(long id, long handledById);

    Complaint updateStatus(long id, ComplaintStatus status);

    Complaint getComplaintById(long id);

    Page<Complaint> getAllComplaints(Pageable pageable);

    Page<Complaint> getComplaintByCitizenId(long citizenId, Pageable pageable);

    Page<Complaint> getComplaintByStatus(ComplaintStatus status, Pageable pageable);

    Page<Complaint> getComplaintByHandlerId(long handlerId, Pageable pageable);

    Page<Complaint> searchComplaints(String keyword, Pageable pageable);

    Page<Complaint> getRecentComplaints(LocalDate since, Pageable pageable);
}