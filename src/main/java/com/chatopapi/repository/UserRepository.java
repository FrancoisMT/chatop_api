package com.chatopapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chatopapi.models.User;

public interface UserRepository extends JpaRepository<User, String> {
	User findByUsername(String username);
    Optional<User> findByEmail(String email);
}
