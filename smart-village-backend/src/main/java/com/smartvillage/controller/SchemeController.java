package com.smartvillage.controller;

import java.security.Principal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/api/schemes")
@Validated
public class SchemeController {

    private final SchemeService schemeService;
    private final UserService userService;

    public SchemeController(SchemeService schemeService, UserService userService) {
        this.schemeService = schemeService;
        this.userService = userService;
    }

    // Create scheme
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<SchemeResponseDto> addScheme(@Valid @RequestBody SchemeRequestDto dto,
                                                       Principal principal) {

        String email = principal.getName();
        User postedBy = userService.getUserByEmail(email);

        Scheme scheme = SchemeMapper.toEntity(dto, postedBy);

        return ResponseEntity.ok(SchemeMapper.toDto(schemeService.createScheme(scheme)));
    }

    // Update scheme
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<SchemeResponseDto> updateScheme(@PathVariable long id,
                                                          @Valid @RequestBody SchemeUpdateDto dto) {

        Scheme scheme = schemeService.getSchemeById(id);
        SchemeMapper.updateEntity(scheme, dto);

        return ResponseEntity.ok(SchemeMapper.toDto(schemeService.updateScheme(id, scheme)));
    }

    // Delete scheme
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteScheme(@PathVariable long id) {
        schemeService.deleteScheme(id);
        return ResponseEntity.ok("Scheme deleted successfully");
    }

    // Deactivate scheme
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}/deactivate")
    public ResponseEntity<SchemeResponseDto> deactivateScheme(@PathVariable long id) {
        return ResponseEntity.ok(SchemeMapper.toDto(schemeService.deactivateScheme(id)));
    }

    // Get scheme by ID
    @GetMapping("/{id}")
    public ResponseEntity<SchemeResponseDto> getSchemeById(@PathVariable long id) {
        return ResponseEntity.ok(SchemeMapper.toDto(schemeService.getSchemeById(id)));
    }

    // Get active schemes (PAGINATED)
    @GetMapping("/active")
    public ResponseEntity<Page<SchemeResponseDto>> getActiveSchemes(Pageable pageable) {

        Page<Scheme> schemes = schemeService.getActiveSchemes(pageable);

        Page<SchemeResponseDto> dtoPage = schemes.map(SchemeMapper::toDto);

        return ResponseEntity.ok(dtoPage);
    }

    // Get all schemes (ADMIN + PAGINATED)
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<Page<SchemeResponseDto>> getAllSchemes(Pageable pageable) {

        Page<Scheme> schemes = schemeService.getAllSchemes(pageable);

        return ResponseEntity.ok(schemes.map(SchemeMapper::toDto));
    }

    // Get schemes by posted user (PAGINATED)
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/posted-by/{postedById}")
    public ResponseEntity<Page<SchemeResponseDto>> getSchemesByPostedById(
            @PathVariable long postedById,
            Pageable pageable) {

        Page<Scheme> schemes = schemeService.getSchemesByPostedById(postedById, pageable);

        return ResponseEntity.ok(schemes.map(SchemeMapper::toDto));
    }

    // Search schemes (PAGINATED)
    @GetMapping("/search")
    public ResponseEntity<Page<SchemeResponseDto>> searchSchemes(
            @RequestParam String name,
            Pageable pageable) {

        Page<Scheme> schemes = schemeService.searchSchemes(name, pageable);

        return ResponseEntity.ok(schemes.map(SchemeMapper::toDto));
    }

    // Filter schemes by eligibility (PAGINATED)
    @GetMapping("/eligibility")
    public ResponseEntity<Page<SchemeResponseDto>> findByEligibility(
            @RequestParam(required = false) Integer financialYear,
            @RequestParam(required = false) String religion,
            @RequestParam(required = false) String casteCategory,
            @RequestParam(required = false) Double annualIncome,
            @RequestParam(required = false) Boolean disability,
            @RequestParam(required = false) String department,
            Pageable pageable) {

        Page<Scheme> schemes = schemeService.findByEligibility(
                financialYear, religion, casteCategory, annualIncome, disability, department, pageable);

        return ResponseEntity.ok(schemes.map(SchemeMapper::toDto));
    }
}