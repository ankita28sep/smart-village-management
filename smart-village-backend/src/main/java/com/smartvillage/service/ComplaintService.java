package com.smartvillage.service;

import java.time.LocalDate;
import java.util.List;

import com.smartvillage.entity.Complaint;
import com.smartvillage.enums.ComplaintStatus;

public interface ComplaintService {
	Complaint raiseComplaint(Complaint complaint);

	Complaint updateComplaint(long id, Complaint complaint);

	void deleteComplaint(long id);

	Complaint assignHandler(long id, long handledBy_id);

	Complaint updateStatus(long id, ComplaintStatus status);

	Complaint getComplaintById(long id);

	List<Complaint> getAllComplaints();

	List<Complaint> getComplaintByCitizenId(long citizenId);

	List<Complaint> getComplaintByStatus(ComplaintStatus status);

	List<Complaint> getComplaintByHandlerId(long handlerId);

	List<Complaint> searchComplaints(String keyword);

	List<Complaint> getRecentComplaints(LocalDate since);

}
