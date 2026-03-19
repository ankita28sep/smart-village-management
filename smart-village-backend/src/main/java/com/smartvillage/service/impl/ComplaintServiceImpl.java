package com.smartvillage.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.smartvillage.entity.Complaint;
import com.smartvillage.entity.User;
import com.smartvillage.enums.ComplaintStatus;
import com.smartvillage.enums.UserRole;
import com.smartvillage.exceptions.InvalidDataException;
import com.smartvillage.exceptions.ResourceNotFoundException;
import com.smartvillage.repository.ComplaintRepository;
import com.smartvillage.service.ComplaintService;
import com.smartvillage.service.UserService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ComplaintServiceImpl implements ComplaintService {
	@Autowired
	private ComplaintRepository complaintRepository;
	@Autowired
	private UserService userService;

	// RAISE COMPLAINT
	@Override
	public Complaint raiseComplaint(Complaint complaint) {

		if (complaint.getCitizen() == null) {
			throw new InvalidDataException("Citizen is required");
		}

		if (!complaint.getCitizen().isActive()) {
			throw new InvalidDataException("User is blocked");
		}

		complaint.setCitizen(userService.getUserById(complaint.getCitizen().getId()));

		complaint.setStatus(ComplaintStatus.PENDING);
		return complaintRepository.save(complaint);

	}

	// UPDATE COMPLAINT
	@Override
	public Complaint updateComplaint(long id, Complaint complaint) {

		return complaintRepository.save(complaint);
	}

	// DELETE COMPLAINT
	@Override
	public void deleteComplaint(long id) {

		Complaint complaint = this.getComplaintIfOwner(id);
		complaintRepository.delete(complaint);
	}

	// ASSIGN HANDLER TO COMPLAINT
	@Override
	public Complaint assignHandler(long id, long handledBy_id) {
		Complaint existingComplaint = complaintRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Complaint", "id", id));
		

		User handler = userService.getUserById(handledBy_id);
		if (!handler.isActive()) {
		    throw new InvalidDataException("Handler is inactive");
		}
		if (handler.getRole() == UserRole.CITIZEN) {
			throw new InvalidDataException("Citizen cannot handle complaints");
		}
		existingComplaint.setHandledBy(handler);
		existingComplaint.setStatus(ComplaintStatus.IN_PROGRESS);

		return complaintRepository.save(existingComplaint);

	}

	// UPDATE COMPLAINT STATUS
	@Override
	public Complaint updateStatus(long id, ComplaintStatus status) {
		Complaint complaint = complaintRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Complaint", "id", id));
		ComplaintStatus oldStatus = complaint.getStatus();
		if ((oldStatus == ComplaintStatus.PENDING || oldStatus == ComplaintStatus.IN_PROGRESS)
				&& (status == ComplaintStatus.RESOLVED || status == ComplaintStatus.REJECTED)) {
			complaint.setStatus(status);
		} else {
			throw new InvalidDataException("Invalid status transition from " + oldStatus + " to " + status);
		}
		return complaintRepository.save(complaint);
	}

	// GET COMPLAINT BY ID
	@Override
	public Complaint getComplaintById(long id) {
		return complaintRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Complaint", "id", id));
	}

	// GET COMPLAINT BY CITIZEN ID
	@Override
	public List<Complaint> getComplaintByCitizenId(long citizenId) {
		return complaintRepository.findByCitizen_Id(citizenId);
	}

	// GET COMPLAINT BY HANDLER ID
	@Override
	public List<Complaint> getComplaintByHandlerId(long handlerId) {
		return complaintRepository.findByHandledBy_Id(handlerId);
	}

	// GET COMPLAINT BY STATUS
	@Override
	public List<Complaint> getComplaintByStatus(ComplaintStatus status) {
		return complaintRepository.findByStatus(status);
	}

	// GET ALL COMPLAINTS
	@Override
	public List<Complaint> getAllComplaints() {
		return complaintRepository.findAll();

	}

	// SEARCH COMPLAINTS BY KEYWORD
	@Override
	public List<Complaint> searchComplaints(String keyword) {
		return complaintRepository.findByTitleContainingIgnoreCase(keyword);
	}

	// GET RECENT COMPLAINTS
	@Override
	public List<Complaint> getRecentComplaints(LocalDate since) {

		return complaintRepository.findByCreatedAtAfter(since);
	}
// GET COMPLAINT IF OWNER
	public Complaint getComplaintIfOwner(long id) {
		Complaint complaint = complaintRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Complaint", "id", id));

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();

		User citizen = userService.getUserByEmail(email);
		if (complaint.getCitizen().getId() != citizen.getId()) {
			throw new InvalidDataException("Access denied: You are not the owner of this complaint");
		}
		return complaint;
	}

}
