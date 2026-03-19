package com.smartvillage.controller;

import java.time.LocalDateTime;
import java.util.List;

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
@RequestMapping("/announcements")
public class AnnouncementController {
	@Autowired
	private AnnouncementService announcementService;
	@Autowired
	private UserService userService;

	@PreAuthorize("hasAnyRole('ADMIN', 'SARPANCH')")
	@PostMapping("/post")
	public AnnouncementResponseDto postAnnouncement(@Valid @RequestBody AnnouncementRequestDto dto) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User user = userService.getUserByEmail(email);
		Announcement a = AnnouncementMapper.toEntity(dto, user);
		return AnnouncementMapper.toDto(announcementService.postAnnouncement(a));
	}

	@PreAuthorize("hasAnyRole('ADMIN', 'SARPANCH')")
	@PutMapping("/update/{id}")
	public AnnouncementResponseDto updateAnnouncement(@PathVariable long id,
			@Valid @RequestBody AnnouncementUpdateDto dto) {
		Announcement existing = announcementService.getAnnouncementById(id);
		AnnouncementMapper.updateEntity(existing, dto);
		return AnnouncementMapper.toDto(announcementService.updateAnnouncement(id, existing));
	}

	@PreAuthorize("hasAnyRole('ADMIN', 'SARPANCH')")
	@DeleteMapping("/delete/{id}")
	public String deleteAnnouncement(@PathVariable long id) {
		announcementService.deleteAnnouncement(id);
		return "Announcement with ID " + id + " has been deleted.";
	}

	@PreAuthorize("hasAnyRole('ADMIN', 'SARPANCH')")
	@GetMapping("/{id}")
	public AnnouncementResponseDto getAnnouncementById(@PathVariable long id) {
		return AnnouncementMapper.toDto(announcementService.getAnnouncementById(id));
	}

	@PreAuthorize("hasAnyRole('ADMIN', 'SARPANCH')")
	@GetMapping("/all")
	public List<AnnouncementResponseDto> getAllAnnouncements() {
		return announcementService.getAllAnnouncements().stream().map(AnnouncementMapper::toDto).toList();
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/by-type/{type}")
	public List<AnnouncementResponseDto> getAnnouncementsByType(@PathVariable AnnouncementType type) {
		return announcementService.getAnnouncementsByType(type).stream().map(AnnouncementMapper::toDto).toList();
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/by-status/{status}")
	public List<AnnouncementResponseDto> getAnnouncementsByStatus(@PathVariable AnnouncementStatus status) {
		return announcementService.getAnnouncementsByStatus(status).stream().map(AnnouncementMapper::toDto).toList();
	}

	@GetMapping("/active")
	public List<AnnouncementResponseDto> getActiveAnnouncements() {
		return announcementService.findByActiveTrue().stream().map(AnnouncementMapper::toDto).toList();
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/postedBy/{postedById}")
	public List<AnnouncementResponseDto> getAnnouncementsPostedBy(@PathVariable long postedById) {
		return announcementService.getAnnouncementsPostedBy(postedById).stream().map(AnnouncementMapper::toDto)
				.toList();
	}


	@GetMapping("/search/{name}")
	public List<AnnouncementResponseDto> searchAnnouncement(@PathVariable String name) {
		return announcementService.searchAnnouncement(name).stream().map(AnnouncementMapper::toDto).toList();
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/recent/{since}")
	public List<AnnouncementResponseDto> getRecentAnnouncements(
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime since) {

		return announcementService.getRecentAnnouncements(since).stream().map(AnnouncementMapper::toDto).toList();
	}

}
