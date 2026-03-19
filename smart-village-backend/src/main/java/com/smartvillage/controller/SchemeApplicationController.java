package com.smartvillage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.smartvillage.dto.schemeApplication.SchemeApplicationRequestDto;
import com.smartvillage.dto.schemeApplication.SchemeApplicationResponseDto;
import com.smartvillage.dto.schemeApplication.SchemeApplicationUpdateDto;
import com.smartvillage.entity.Scheme;
import com.smartvillage.entity.SchemeApplication;
import com.smartvillage.entity.User;
import com.smartvillage.enums.ApplicationStatus;
import com.smartvillage.mapper.SchemeApplicationMapper;
import com.smartvillage.service.SchemeApplicationService;
import com.smartvillage.service.SchemeService;
import com.smartvillage.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/schemeApplications")
@Validated
public class SchemeApplicationController {
	@Autowired
	private SchemeApplicationService applicationService;
	@Autowired
	private UserService userService;
	@Autowired
	private SchemeService schemeService;

	@PreAuthorize("hasRole('CITIZEN')")
	@PostMapping("/apply")
	public SchemeApplicationResponseDto apply(@Valid @RequestBody SchemeApplicationRequestDto dto) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		String email = auth.getName();

		User user = userService.getUserByEmail(email);
		Scheme scheme = schemeService.getSchemeById(dto.getSchemeId());
		SchemeApplication application = SchemeApplicationMapper.toEntity(dto, user, scheme);
		return SchemeApplicationMapper.toDto(applicationService.applyToScheme(application));
	}

	@PreAuthorize("hasRole('CITIZEN')")
	@PutMapping("/update/{id}")
	public SchemeApplicationResponseDto updateApplication(@PathVariable long id,
			@Valid @RequestBody SchemeApplicationUpdateDto dto) {

		SchemeApplication application = applicationService.getApplicationById(id);
		SchemeApplicationMapper.updateEntity(application, dto);

		return SchemeApplicationMapper.toDto(applicationService.updateApplication(id, application));
	}

	@PreAuthorize("hasAnyRole('ADMIN','SARPANCH')")
	@PutMapping("/update-status/{id}/{status}")
	public SchemeApplicationResponseDto updateStatus(@PathVariable long id, @PathVariable ApplicationStatus status) {
		return SchemeApplicationMapper.toDto(applicationService.updateApplicationStatus(id, status));
	}

	@PreAuthorize("hasRole('CITIZEN')")
	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable long id) {
		applicationService.deleteApplication(id);
		return "Scheme application with ID " + id + " has been deleted.";
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/byId/{id}")
	public SchemeApplicationResponseDto getApplicationById(@PathVariable long id) {
		return SchemeApplicationMapper.toDto(applicationService.getApplicationById(id));
	}

	@PreAuthorize("hasAnyRole('ADMIN','SARPANCH')")
	@GetMapping("/all")
	public List<SchemeApplicationResponseDto> getAllApplications() {
		return applicationService.getAllApplications().stream().map(SchemeApplicationMapper::toDto).toList();
	}

	@PreAuthorize("hasRole('CITIZEN')")
	@GetMapping("/my")
	public List<SchemeApplicationResponseDto> getApplicationsByApplicantId() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		String email = auth.getName();

		User user = userService.getUserByEmail(email);
		return applicationService.getApplicationsByApplicantId(user.getId()).stream()
				.map(SchemeApplicationMapper::toDto).toList();
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/by-schemeId/{schemeId}")
	public List<SchemeApplicationResponseDto> getApplicationsBySchemeId(@PathVariable long schemeId) {
		return applicationService.getApplicationsBySchemeId(schemeId).stream().map(SchemeApplicationMapper::toDto)
				.toList();
	}

	@PreAuthorize("hasAnyRole('ADMIN','SARPANCH')")
	@GetMapping("/by-status/{status}")
	public List<SchemeApplicationResponseDto> getApplicationsByStatus(@PathVariable ApplicationStatus status) {
		return applicationService.getApplicationsByStatus(status).stream().map(SchemeApplicationMapper::toDto).toList();
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/by-year/{year}")
	public List<SchemeApplicationResponseDto> getApplicationsByYear(@PathVariable int year) {
		return applicationService.getApplicationsByYear(year).stream().map(SchemeApplicationMapper::toDto).toList();
	}

	@PreAuthorize("hasAnyRole('ADMIN','SARPANCH')")
	@PutMapping("/cancel/{id}")
	public SchemeApplicationResponseDto cancelApplication(@PathVariable long id) {
		return SchemeApplicationMapper.toDto(applicationService.cancelApplication(id));

	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/find/{applicantId}/{schemeId}")
	public SchemeApplicationResponseDto findByApplicant_IdAndScheme_Id(@PathVariable long applicantId,
			@PathVariable long schemeId) {
		return SchemeApplicationMapper.toDto(applicationService.findByApplicant_IdAndScheme_Id(applicantId, schemeId));
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/find-by-applicant/{applicantName}")
	public List<SchemeApplicationResponseDto> searchApplicationsByApplicantName(@PathVariable String applicantName) {
		return applicationService.searchApplicationsByApplicantName(applicantName).stream()
				.map(SchemeApplicationMapper::toDto).toList();
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/by-schemeName/{schemeName}")
	public List<SchemeApplicationResponseDto> searchApplicationsBySchemeName(@PathVariable String schemeName) {
		return applicationService.searchApplicationsBySchemeName(schemeName).stream()
				.map(SchemeApplicationMapper::toDto).toList();
	}

}
