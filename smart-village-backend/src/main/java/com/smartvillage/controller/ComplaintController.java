package com.smartvillage.controller;

import java.security.Principal;
import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.smartvillage.dto.complaint.ComplaintRequestDto;
import com.smartvillage.dto.complaint.ComplaintResponseDto;
import com.smartvillage.dto.complaint.ComplaintUpdateDto;
import com.smartvillage.entity.Complaint;
import com.smartvillage.entity.User;
import com.smartvillage.enums.ComplaintStatus;
import com.smartvillage.mapper.ComplaintMapper;
import com.smartvillage.service.ComplaintService;
import com.smartvillage.service.UserService;

import jakarta.validation.Valid;

@RestController
@Validated
@RequestMapping("/api/complaints")
public class ComplaintController {

    private final ComplaintService complaintService;
    private final UserService userService;

    public ComplaintController(ComplaintService complaintService, UserService userService) {
        this.complaintService = complaintService;
        this.userService = userService;
    }

    // Raise complaint (Citizen)
    @PreAuthorize("hasRole('CITIZEN')")
    @PostMapping
    public ResponseEntity<ComplaintResponseDto> raiseComplaint(
            Principal principal,
            @Valid @RequestBody ComplaintRequestDto dto) {

        String email = principal.getName();
        User citizen = userService.getUserByEmail(email);

        Complaint complaint = ComplaintMapper.toEntity(dto, citizen);
        return ResponseEntity.ok(
                ComplaintMapper.toDto(complaintService.raiseComplaint(complaint))
        );
    }

    // Update complaint (Citizen)
    @PreAuthorize("hasRole('CITIZEN')")
    @PutMapping("/{id}")
    public ResponseEntity<ComplaintResponseDto> updateComplaint(
            @PathVariable long id,
            @Valid @RequestBody ComplaintUpdateDto dto) {

        Complaint complaint = complaintService.getComplaintById(id);
        ComplaintMapper.updateEntity(complaint, dto);

        return ResponseEntity.ok(
                ComplaintMapper.toDto(complaintService.updateComplaint(id, complaint))
        );
    }

    // Delete complaint
    @PreAuthorize("hasRole('CITIZEN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComplaint(@PathVariable long id) {
        complaintService.deleteComplaint(id);
        return ResponseEntity.noContent().build();
    }

    // Assign handler
    @PreAuthorize("hasAnyRole('ADMIN','SARPANCH')")
    @PutMapping("/{id}/assign/{handlerId}")
    public ResponseEntity<ComplaintResponseDto> assignHandler(
            @PathVariable long id,
            @PathVariable long handlerId) {

        return ResponseEntity.ok(
                ComplaintMapper.toDto(complaintService.assignHandler(id, handlerId))
        );
    }

    // Update status
    @PreAuthorize("hasAnyRole('ADMIN','SARPANCH')")
    @PutMapping("/{id}/status")
    public ResponseEntity<ComplaintResponseDto> updateStatus(
            @PathVariable long id,
            @RequestParam ComplaintStatus status) {

        return ResponseEntity.ok(
                ComplaintMapper.toDto(complaintService.updateStatus(id, status))
        );
    }

    // Get by ID
    @PreAuthorize("hasAnyRole('ADMIN','SARPANCH')")
    @GetMapping("/{id}")
    public ResponseEntity<ComplaintResponseDto> getComplaintById(@PathVariable long id) {
        return ResponseEntity.ok(
                ComplaintMapper.toDto(complaintService.getComplaintById(id))
        );
    }

    // Get all complaints (pagination)
    @PreAuthorize("hasAnyRole('ADMIN','SARPANCH')")
    @GetMapping
    public ResponseEntity<Page<ComplaintResponseDto>> getAllComplaints(Pageable pageable) {
        return ResponseEntity.ok(
                complaintService.getAllComplaints(pageable)
                        .map(ComplaintMapper::toDto)
        );
    }

    // My complaints
    @PreAuthorize("hasRole('CITIZEN')")
    @GetMapping("/my")
    public ResponseEntity<Page<ComplaintResponseDto>> getMyComplaints(
            Principal principal,
            Pageable pageable) {

        String email = principal.getName();
        User citizen = userService.getUserByEmail(email);

        return ResponseEntity.ok(
                complaintService.getComplaintByCitizenId(citizen.getId(), pageable)
                        .map(ComplaintMapper::toDto)
        );
    }

    // By status
    @PreAuthorize("hasAnyRole('ADMIN','SARPANCH')")
    @GetMapping("/status")
    public ResponseEntity<Page<ComplaintResponseDto>> getByStatus(
            @RequestParam ComplaintStatus status,
            Pageable pageable) {

        return ResponseEntity.ok(
                complaintService.getComplaintByStatus(status, pageable)
                        .map(ComplaintMapper::toDto)
        );
    }

    // By handler
    @PreAuthorize("hasAnyRole('ADMIN','SARPANCH')")
    @GetMapping("/handler/{handlerId}")
    public ResponseEntity<Page<ComplaintResponseDto>> getByHandler(
            @PathVariable long handlerId,
            Pageable pageable) {

        return ResponseEntity.ok(
                complaintService.getComplaintByHandlerId(handlerId, pageable)
                        .map(ComplaintMapper::toDto)
        );
    }

    // Search
    @PreAuthorize("hasAnyRole('ADMIN','SARPANCH')")
    @GetMapping("/search")
    public ResponseEntity<Page<ComplaintResponseDto>> searchComplaints(
            @RequestParam String title,
            Pageable pageable) {

        return ResponseEntity.ok(
                complaintService.searchComplaints(title, pageable)
                        .map(ComplaintMapper::toDto)
        );
    }

    // Recent complaints
    @PreAuthorize("hasAnyRole('ADMIN','SARPANCH')")
    @GetMapping("/recent")
    public ResponseEntity<Page<ComplaintResponseDto>> getRecentComplaints(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate since,
            Pageable pageable) {

        return ResponseEntity.ok(
                complaintService.getRecentComplaints(since, pageable)
                        .map(ComplaintMapper::toDto)
        );
    }
}