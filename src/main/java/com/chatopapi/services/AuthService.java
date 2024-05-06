package com.chatopapi.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.chatopapi.dto.UserDTO;
import com.chatopapi.models.User;
import com.chatopapi.repository.UserRepository;

@Service
public class AuthService {
	
	@Autowired
	private UserRepository userRepository;
		
	@Autowired
	private JWTService jwtService;
	
	public void registerUser(UserDTO userDTO) {
	 Optional<User> existingUser = userRepository.findByEmail(userDTO.getEmail());
	}
	
	
}
