package com.smartvillage.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/complaints")
public class ComplaintController {
	@Autowired
	private ComplaintService complaintService;
	@Autowired
	private UserService userService;

	@PreAuthorize("hasRole('CITIZEN')")
	@PostMapping("/raise")
	public ComplaintResponseDto raiseComplaint(@Valid @RequestBody ComplaintRequestDto dto) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();

		User citizen = userService.getUserByEmail(email);
		Complaint complaint = ComplaintMapper.toEntity(dto, citizen);
		return ComplaintMapper.toDto(complaintService.raiseComplaint(complaint));
	}

	@PreAuthorize("hasRole('CITIZEN')")
	@PutMapping("/update/{id}")
	public ComplaintResponseDto updateComplaint(@PathVariable long id, @Valid @RequestBody ComplaintUpdateDto dto) {
		Complaint complaint = complaintService.getComplaintById(id);
		ComplaintMapper.updateEntity(complaint, dto);
		return ComplaintMapper.toDto(complaintService.updateComplaint(id, complaint));

	}

	@PreAuthorize("hasRole('CITIZEN')")
	@DeleteMapping("/delete/{id}")
	public String deleteComplaint(@PathVariable long id) {
		complaintService.deleteComplaint(id);
		return "Complaint with ID " + id + " has been deleted.";
	}

	@PreAuthorize("hasAnyRole('ADMIN','SARPANCH')")
	@PutMapping("/assignHandler/{id}/{handledBy_id}")
	public ComplaintResponseDto assignHandler(@PathVariable long id, @PathVariable long handledBy_id) {

		return ComplaintMapper.toDto(complaintService.assignHandler(id, handledBy_id));
	}

	@PreAuthorize("hasAnyRole('ADMIN','SARPANCH')")
	@PutMapping("/update-status/{id}/{status}")
	public ComplaintResponseDto updateStatus(@PathVariable long id, @PathVariable ComplaintStatus status) {

		return ComplaintMapper.toDto(complaintService.updateStatus(id, status));
	}

	@PreAuthorize("hasAnyRole('ADMIN','SARPANCH')")
	@GetMapping("/get/{id}")
	public ComplaintResponseDto getComplaintById(@PathVariable long id) {

		return ComplaintMapper.toDto(complaintService.getComplaintById(id));
	}

	@PreAuthorize("hasAnyRole('ADMIN','SARPANCH')")
	@GetMapping("/all")
	public List<ComplaintResponseDto> getAllComplaints() {
		return complaintService.getAllComplaints().stream().map(ComplaintMapper::toDto).collect(Collectors.toList());

	}

	@PreAuthorize("hasRole('CITIZEN')")
	@GetMapping("/my")
	public List<ComplaintResponseDto> getComplaintByCitizenId() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();

		User citizen = userService.getUserByEmail(email);
		return complaintService.getComplaintByCitizenId(citizen.getId()).stream().map(ComplaintMapper::toDto)
				.collect(Collectors.toList());

	}
	@PreAuthorize("hasAnyRole('ADMIN','SARPANCH')")
	@GetMapping("/by-status/{status}")
	public List<ComplaintResponseDto> getComplaintByStatus(@PathVariable ComplaintStatus status) {
		return complaintService.getComplaintByStatus(status).stream().map(ComplaintMapper::toDto)
				.collect(Collectors.toList());

	}

	@PreAuthorize("hasAnyRole('ADMIN','SARPANCH')")
	@GetMapping("/by-handler/{handler_id}")
	public List<ComplaintResponseDto> getComplaintByHandlerId(@PathVariable long handler_id) {
		return complaintService.getComplaintByHandlerId(handler_id).stream().map(ComplaintMapper::toDto)
				.collect(Collectors.toList());

	}

	@PreAuthorize("hasAnyRole('ADMIN','SARPANCH')")
	@GetMapping("/search/{title}")
	public List<ComplaintResponseDto> searchComplaints(@PathVariable String title) {
		return complaintService.searchComplaints(title).stream().map(ComplaintMapper::toDto)
				.collect(Collectors.toList());

	}

	@PreAuthorize("hasAnyRole('ADMIN','SARPANCH')")
	@GetMapping("/recent/{since}")
	public List<ComplaintResponseDto> getRecentComplaints(
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate since) {
		return complaintService.getRecentComplaints(since).stream().map(ComplaintMapper::toDto)
				.collect(Collectors.toList());

	}

}
