package com.smartvillage.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping("/applications")
@Validated
public class SchemeApplicationController {

    private final SchemeApplicationService applicationService;
    private final UserService userService;
    private final SchemeService schemeService;

    public SchemeApplicationController(SchemeApplicationService applicationService,
                                       UserService userService,
                                       SchemeService schemeService) {
        this.applicationService = applicationService;
        this.userService = userService;
        this.schemeService = schemeService;
    }

    // APPLY TO SCHEME
    @PreAuthorize("hasRole('CITIZEN')")
    @PostMapping
    public ResponseEntity<SchemeApplicationResponseDto> apply(
            @Valid @RequestBody SchemeApplicationRequestDto dto,
            java.security.Principal principal) {

        User user = userService.getUserByEmail(principal.getName());
        Scheme scheme = schemeService.getSchemeById(dto.getSchemeId());

        SchemeApplication application =
                SchemeApplicationMapper.toEntity(dto, user, scheme);

        return ResponseEntity.ok(
                SchemeApplicationMapper.toDto(
                        applicationService.applyToScheme(application)
                )
        );
    }

    // UPDATE APPLICATION
    @PreAuthorize("hasRole('CITIZEN')")
    @PutMapping("/{id}")
    public ResponseEntity<SchemeApplicationResponseDto> updateApplication(
            @PathVariable long id,
            @Valid @RequestBody SchemeApplicationUpdateDto dto) {

        SchemeApplication application = applicationService.getApplicationById(id);
        SchemeApplicationMapper.updateEntity(application, dto);

        return ResponseEntity.ok(
                SchemeApplicationMapper.toDto(
                        applicationService.updateApplication(id, application)
                )
        );
    }

    // UPDATE STATUS (ADMIN/SARPANCH)
    @PreAuthorize("hasAnyRole('ADMIN','SARPANCH')")
    @PutMapping("/{id}/status/{status}")
    public ResponseEntity<SchemeApplicationResponseDto> updateStatus(
            @PathVariable long id,
            @PathVariable ApplicationStatus status) {

        return ResponseEntity.ok(
                SchemeApplicationMapper.toDto(
                        applicationService.updateApplicationStatus(id, status)
                )
        );
    }

    // DELETE APPLICATION
    @PreAuthorize("hasRole('CITIZEN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        applicationService.deleteApplication(id);
        return ResponseEntity.ok("Application deleted successfully");
    }

    // GET BY ID
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<SchemeApplicationResponseDto> getById(@PathVariable long id) {
        return ResponseEntity.ok(
                SchemeApplicationMapper.toDto(
                        applicationService.getApplicationById(id)
                )
        );
    }

    // GET ALL (PAGINATED)
    @PreAuthorize("hasAnyRole('ADMIN','SARPANCH')")
    @GetMapping
    public ResponseEntity<Page<SchemeApplicationResponseDto>> getAll(Pageable pageable) {
        return ResponseEntity.ok(
                applicationService.getAllApplications(pageable)
                        .map(SchemeApplicationMapper::toDto)
        );
    }

    // GET MY APPLICATIONS (PAGINATED)
    @PreAuthorize("hasRole('CITIZEN')")
    @GetMapping("/my")
    public ResponseEntity<Page<SchemeApplicationResponseDto>> getMyApplications(
            Pageable pageable,
            java.security.Principal principal) {

        User user = userService.getUserByEmail(principal.getName());

        return ResponseEntity.ok(
                applicationService.getApplicationsByApplicantId(user.getId(), pageable)
                        .map(SchemeApplicationMapper::toDto)
        );
    }

    // BY SCHEME ID (PAGINATED)
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/scheme/{schemeId}")
    public ResponseEntity<Page<SchemeApplicationResponseDto>> getBySchemeId(
            @PathVariable long schemeId,
            Pageable pageable) {

        return ResponseEntity.ok(
                applicationService.getApplicationsBySchemeId(schemeId, pageable)
                        .map(SchemeApplicationMapper::toDto)
        );
    }

    // BY STATUS (PAGINATED)
    @PreAuthorize("hasAnyRole('ADMIN','SARPANCH')")
    @GetMapping("/status/{status}")
    public ResponseEntity<Page<SchemeApplicationResponseDto>> getByStatus(
            @PathVariable ApplicationStatus status,
            Pageable pageable) {

        return ResponseEntity.ok(
                applicationService.getApplicationsByStatus(status, pageable)
                        .map(SchemeApplicationMapper::toDto)
        );
    }

    // CANCEL APPLICATION
    @PreAuthorize("hasAnyRole('ADMIN','SARPANCH')")
    @PutMapping("/{id}/cancel")
    public ResponseEntity<SchemeApplicationResponseDto> cancel(@PathVariable long id) {
        return ResponseEntity.ok(
                SchemeApplicationMapper.toDto(
                        applicationService.cancelApplication(id)
                )
        );
    }
}