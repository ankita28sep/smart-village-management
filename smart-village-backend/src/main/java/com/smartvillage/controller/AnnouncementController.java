package com.smartvillage.controller;

import java.security.Principal;
import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.smartvillage.dto.announcement.AnnouncementRequestDto;
import com.smartvillage.dto.announcement.AnnouncementResponseDto;
import com.smartvillage.dto.announcement.AnnouncementUpdateDto;
import com.smartvillage.entity.Announcement;
import com.smartvillage.entity.User;
import com.smartvillage.enums.AnnouncementStatus;
import com.smartvillage.enums.AnnouncementType;
import com.smartvillage.mapper.AnnouncementMapper;
import com.smartvillage.service.AnnouncementService;
import com.smartvillage.service.UserService;

import jakarta.validation.Valid;

@RestController
@Validated
@RequestMapping("/api/announcements")
public class AnnouncementController {

    private final AnnouncementService announcementService;
    private final UserService userService;

    public AnnouncementController(AnnouncementService announcementService,
                                  UserService userService) {
        this.announcementService = announcementService;
        this.userService = userService;
    }

    // Create announcement
    @PreAuthorize("hasAnyRole('ADMIN', 'SARPANCH')")
    @PostMapping("/post")
    public ResponseEntity<AnnouncementResponseDto> postAnnouncement(
            Principal principal,
            @Valid @RequestBody AnnouncementRequestDto dto) {

        String email = principal.getName();
        User user = userService.getUserByEmail(email);

        Announcement announcement = AnnouncementMapper.toEntity(dto, user);

        return ResponseEntity.ok(
                AnnouncementMapper.toDto(
                        announcementService.postAnnouncement(announcement)
                )
        );
    }

    // Update
    @PreAuthorize("hasAnyRole('ADMIN', 'SARPANCH')")
    @PutMapping("/{id}")
    public ResponseEntity<AnnouncementResponseDto> updateAnnouncement(
            @PathVariable long id,
            @Valid @RequestBody AnnouncementUpdateDto dto) {

        Announcement existing = announcementService.getAnnouncementById(id);
        AnnouncementMapper.updateEntity(existing, dto);

        return ResponseEntity.ok(
                AnnouncementMapper.toDto(
                        announcementService.updateAnnouncement(id, existing)
                )
        );
    }

    // Delete
    @PreAuthorize("hasAnyRole('ADMIN', 'SARPANCH')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnnouncement(@PathVariable long id) {
        announcementService.deleteAnnouncement(id);
        return ResponseEntity.noContent().build();
    }

    // Get by ID
    @PreAuthorize("hasAnyRole('ADMIN', 'SARPANCH')")
    @GetMapping("/{id}")
    public ResponseEntity<AnnouncementResponseDto> getAnnouncementById(@PathVariable long id) {
        return ResponseEntity.ok(
                AnnouncementMapper.toDto(announcementService.getAnnouncementById(id))
        );
    }

    // Get all (pagination)
    @PreAuthorize("hasAnyRole('ADMIN', 'SARPANCH')")
    @GetMapping
    public ResponseEntity<Page<AnnouncementResponseDto>> getAllAnnouncements(Pageable pageable) {
        return ResponseEntity.ok(
                announcementService.getAllAnnouncements(pageable)
                        .map(AnnouncementMapper::toDto)
        );
    }

    // By type
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/type")
    public ResponseEntity<Page<AnnouncementResponseDto>> getByType(
            @RequestParam AnnouncementType type,
            Pageable pageable) {

        return ResponseEntity.ok(
                announcementService.getAnnouncementsByType(type, pageable)
                        .map(AnnouncementMapper::toDto)
        );
    }

    // By status
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/status")
    public ResponseEntity<Page<AnnouncementResponseDto>> getByStatus(
            @RequestParam AnnouncementStatus status,
            Pageable pageable) {

        return ResponseEntity.ok(
                announcementService.getAnnouncementsByStatus(status, pageable)
                        .map(AnnouncementMapper::toDto)
        );
    }

    // Active announcements (public)
    @GetMapping("/active")
    public ResponseEntity<Page<AnnouncementResponseDto>> getActiveAnnouncements(Pageable pageable) {
        return ResponseEntity.ok(
                announcementService.findByActiveTrue(pageable)
                        .map(AnnouncementMapper::toDto)
        );
    }

    // By posted user
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/posted-by/{userId}")
    public ResponseEntity<Page<AnnouncementResponseDto>> getPostedBy(
            @PathVariable long userId,
            Pageable pageable) {

        return ResponseEntity.ok(
                announcementService.getAnnouncementsPostedBy(userId, pageable)
                        .map(AnnouncementMapper::toDto)
        );
    }

    // Search
    @GetMapping("/search")
    public ResponseEntity<Page<AnnouncementResponseDto>> search(
            @RequestParam String keyword,
            Pageable pageable) {

        return ResponseEntity.ok(
                announcementService.searchAnnouncement(keyword, pageable)
                        .map(AnnouncementMapper::toDto)
        );
    }

    // Recent
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/recent")
    public ResponseEntity<Page<AnnouncementResponseDto>> getRecent(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime since,
            Pageable pageable) {

        return ResponseEntity.ok(
                announcementService.getRecentAnnouncements(since, pageable)
                        .map(AnnouncementMapper::toDto)
        );
    }

    // Activate / Inactivate (change status)
    @PreAuthorize("hasAnyRole('ADMIN', 'SARPANCH')")
    @PatchMapping("/{id}/status")
    public ResponseEntity<AnnouncementResponseDto> changeStatus(
            @PathVariable long id,
            @RequestParam AnnouncementStatus status) {

        return ResponseEntity.ok(
                AnnouncementMapper.toDto(
                        announcementService.changeStatus(id, status)
                )
        );
    }
}