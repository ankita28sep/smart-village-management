package com.smartvillage.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartvillage.entity.User;
import com.smartvillage.enums.UserRole;

public interface UserRepository extends JpaRepository<User, Long> {

	// AUTHENTICATION
	boolean existsById(long id);

	Optional<User> findByEmail(String email);

	boolean existsByEmail(String email);

	// FILTERING
	List<User> findByRole(UserRole role);

	boolean existsByEmailAndIdNot(String email, Long id);

	List<User> findByActiveTrue();

	Optional<User> findByName(String name);

}
