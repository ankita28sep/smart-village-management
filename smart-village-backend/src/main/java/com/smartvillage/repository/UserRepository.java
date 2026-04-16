package com.smartvillage.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.smartvillage.entity.User;
import com.smartvillage.enums.UserRole;

public interface UserRepository extends JpaRepository<User, Long> {

    // AUTHENTICATION
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByEmailAndIdNot(String email, Long id);

    // PAGINATED FILTERING
    Page<User> findByRole(UserRole role, Pageable pageable);

    Page<User> findByActiveTrue(Pageable pageable);

    Page<User> findByNameContainingIgnoreCase(String name, Pageable pageable);
}