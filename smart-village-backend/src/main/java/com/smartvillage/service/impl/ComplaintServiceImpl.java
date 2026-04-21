package com.smartvillage.service.impl;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.smartvillage.entity.Complaint;
import com.smartvillage.entity.User;
import com.smartvillage.enums.ComplaintStatus;
import com.smartvillage.enums.UserRole;
import com.smartvillage.exceptions.DuplicateResourceException;
import com.smartvillage.exceptions.InvalidDataException;
import com.smartvillage.exceptions.ResourceNotFoundException;
import com.smartvillage.repository.ComplaintRepository;
import com.smartvillage.service.ComplaintService;
import com.smartvillage.service.UserService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ComplaintServiceImpl implements ComplaintService {

    private final ComplaintRepository complaintRepository;
    private final UserService userService;

    public ComplaintServiceImpl(ComplaintRepository complaintRepository,
                                UserService userService) {
        this.complaintRepository = complaintRepository;
        this.userService = userService;
    }

    // RAISE COMPLAINT
    @Override
    public Complaint raiseComplaint(Complaint complaint) {

        if (complaint.getCitizen() == null) {
            throw new InvalidDataException("Citizen is required");
        }

        // Prevent duplicate titles (case-insensitive)
        if (complaintRepository.existsByTitleIgnoreCase(complaint.getTitle())) {
            throw new DuplicateResourceException(
                    "Complaint with title '" + complaint.getTitle() + "' already exists");
        }

        User citizen = userService.getUserById(complaint.getCitizen().getId());

        if (!citizen.isActive()) {
            throw new InvalidDataException("User is blocked");
        }

        complaint.setCitizen(citizen);
        complaint.setStatus(ComplaintStatus.PENDING);

        return complaintRepository.save(complaint);
    }

    @Override
    public Complaint updateComplaint(long id, Complaint complaint) {

        // Prevent duplicate titles when updating: allow same title for this id but not others
        if (complaintRepository.existsByTitleIgnoreCaseAndIdNot(complaint.getTitle(), id)) {
            throw new DuplicateResourceException(
                    "Complaint with title '" + complaint.getTitle() + "' already exists");
        }

        complaint.setId(id);
        return complaintRepository.save(complaint);
    }

    // DELETE COMPLAINT (only owner)
    @Override
    public void deleteComplaint(long id) {
        Complaint complaint = getComplaintById(id);
        complaintRepository.delete(complaint);
    }

    // ASSIGN HANDLER
    @Override
    public Complaint assignHandler(long id, long handlerId) {

        Complaint complaint = getComplaintById(id);

        User handler = userService.getUserById(handlerId);

        if (!handler.isActive()) {
            throw new InvalidDataException("Handler is inactive");
        }

        if (handler.getRole() == UserRole.CITIZEN) {
            throw new InvalidDataException("Citizen cannot handle complaints");
        }

        complaint.setHandledBy(handler);
        complaint.setStatus(ComplaintStatus.IN_PROGRESS);

        return complaintRepository.save(complaint);
    }
    // PAGINATED METHODS

    @Override
    public Page<Complaint> getAllComplaints(Pageable pageable) {
        return complaintRepository.findAll(pageable);
    }

    // UPDATE STATUS
    @Override
    public Complaint updateStatus(long id, ComplaintStatus status) {

        Complaint complaint = getComplaintById(id);

        ComplaintStatus oldStatus = complaint.getStatus();

        if ((oldStatus == ComplaintStatus.PENDING || oldStatus == ComplaintStatus.IN_PROGRESS)
                && (status == ComplaintStatus.RESOLVED || status == ComplaintStatus.REJECTED)) {

            complaint.setStatus(status);

        } else {
            throw new InvalidDataException(
                    "Invalid status transition from " + oldStatus + " to " + status);
        }

        return complaintRepository.save(complaint);
    }

    // GET BY ID
    @Override
    public Complaint getComplaintById(long id) {
        return complaintRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Complaint", "id", id));
    }

   

    @Override
    public Page<Complaint> getComplaintByCitizenId(long citizenId, Pageable pageable) {
        return complaintRepository.findByCitizen_Id(citizenId, pageable);
    }

    @Override
    public Page<Complaint> getComplaintByHandlerId(long handlerId, Pageable pageable) {
        return complaintRepository.findByHandledBy_Id(handlerId, pageable);
    }

    @Override
    public Page<Complaint> getComplaintByStatus(ComplaintStatus status, Pageable pageable) {
        return complaintRepository.findByStatus(status, pageable);
    }

    @Override
    public Page<Complaint> searchComplaints(String keyword, Pageable pageable) {
        return complaintRepository.findByTitleContainingIgnoreCase(keyword, pageable);
    }

    @Override
    public Page<Complaint> getRecentComplaints(LocalDate since, Pageable pageable) {
        return complaintRepository.findByCreatedAtAfter(since, pageable);
    }
}