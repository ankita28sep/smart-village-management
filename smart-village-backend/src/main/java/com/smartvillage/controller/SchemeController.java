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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smartvillage.dto.scheme.SchemeRequestDto;
import com.smartvillage.dto.scheme.SchemeResponseDto;
import com.smartvillage.dto.scheme.SchemeUpdateDto;
import com.smartvillage.entity.Scheme;
import com.smartvillage.entity.User;
import com.smartvillage.mapper.SchemeMapper;
import com.smartvillage.service.SchemeService;
import com.smartvillage.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/schemes")
@Validated
public class SchemeController {
	@Autowired
	private SchemeService schemeService;
	@Autowired
	private UserService userService;

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/add")
	public SchemeResponseDto addScheme(@Valid @RequestBody SchemeRequestDto dto) {
		Authentication auth=SecurityContextHolder
				.getContext()
				.getAuthentication();
		String email=auth.getName();
		User postedBy = userService.getUserByEmail(email);
		Scheme scheme = SchemeMapper.toEntity(dto, postedBy);
		return SchemeMapper.toDto(schemeService.createScheme(scheme));
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/update/{id}")
	public SchemeResponseDto updateScheme(@PathVariable long id, @Valid @RequestBody SchemeUpdateDto dto) {
		Scheme scheme = schemeService.getSchemeById(id);
		SchemeMapper.updateEntity(scheme, dto);
		return SchemeMapper.toDto(schemeService.updateScheme(id, scheme));
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/delete/{id}")
	public String deleteScheme(@PathVariable long id) {
		schemeService.deleteScheme(id);
		return "Scheme deleted successfully";
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/deactivate/{id}")
	public SchemeResponseDto deactivateScheme(@PathVariable long id) {
		return SchemeMapper.toDto(schemeService.deactivateScheme(id));
	}

	@GetMapping("/{id}")
	public SchemeResponseDto getSchemeById(@PathVariable long id) {
		return SchemeMapper.toDto(schemeService.getSchemeById(id));
	}

	@GetMapping("/active")
	public List<SchemeResponseDto> getActiveSchemes() {
		return schemeService.getActiveSchemes().stream().map(SchemeMapper::toDto).toList();
	}
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/all")
	public List<SchemeResponseDto> getAllSchemes() {
		return schemeService.getAllSchemes().stream().map(SchemeMapper::toDto).toList();
	}
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/posted-by/{postedById}")
	public List<SchemeResponseDto> getSchemesByPostedBy_Id(@PathVariable long postedById) {
		return schemeService.getSchemesByPostedById(postedById).stream().map(SchemeMapper::toDto).toList();
	}

	@GetMapping("/search/{name}")
	public List<SchemeResponseDto> searchSchemes(@PathVariable String name) {
		return schemeService.searchSchemes(name).stream().map(SchemeMapper::toDto).toList();
	}

	@GetMapping("/by-eligibility")
	public List<SchemeResponseDto> findByEligibility(@RequestParam(required = false) Integer financialYear,
			@RequestParam(required = false) String religion, @RequestParam(required = false) String casteCategory,
			@RequestParam(required = false) Double annualIncome, @RequestParam(required = false) Boolean disability,
			@RequestParam(required = false) String department) {
		return schemeService
				.findByEligibility(financialYear, religion, casteCategory, annualIncome, disability, department)
				.stream().map(SchemeMapper::toDto).toList();
	}

}
