package com.sportseventapplication.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sportseventapplication.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	public Optional<User> findByusername(String username);

	public boolean existsByUsername(String username);
	
}
