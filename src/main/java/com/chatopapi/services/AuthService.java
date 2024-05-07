package com.chatopapi.services;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.chatopapi.dto.UserDTO;
import com.chatopapi.dto.UserInfoDTO;
import com.chatopapi.exceptions.CustomException;
import com.chatopapi.models.User;
import com.chatopapi.repository.UserRepository;


@Service
public class AuthService {
	 private final UserRepository userRepository;
	 private final PasswordEncoder passwordEncoder;
	 private final AuthenticationManager authenticationManager;
	 
	 public AuthService(
		        UserRepository userRepository,
		        AuthenticationManager authenticationManager,
		        PasswordEncoder passwordEncoder
		    ) {
		        this.authenticationManager = authenticationManager;
		        this.userRepository = userRepository;
		        this.passwordEncoder = passwordEncoder;
	 			}
	
	 
	 public User registerUser(UserDTO userDTO) {
		 
		try {
			
			if (userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
				throw new CustomException("Email already in use", HttpStatus.BAD_REQUEST);
			}
			
			User user = new User();
			user.setName(userDTO.getName());
			user.setEmail(userDTO.getEmail());			
			user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
			
			userRepository.save(user);	
			
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), userDTO.getPassword()));
			return userRepository.findByEmail(user.getEmail())
					.orElseThrow(() -> new RuntimeException("User not found"));
			
		} catch(CustomException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
	        throw new CustomException("Error while saving user data", HttpStatus.BAD_REQUEST);
		}
		
		
	 }
	
	 public User login(UserDTO userDTO) {
		
		try {
			
			User user = new User();
			user.setEmail(userDTO.getEmail());
			
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDTO.getEmail(), userDTO.getPassword()));			
			return userRepository.findByEmail(user.getEmail())
					.orElseThrow(() -> new CustomException("User not found", HttpStatus.BAD_REQUEST));				
		
		} catch (CustomException e) {
	        throw e;
		} catch (AuthenticationException e) {
	        throw new CustomException("Invalid Credentials", HttpStatus.BAD_REQUEST);
		}
	 }
	 
	 public UserInfoDTO authenticatedUser() {
		 
		 try {
			 
			 UserInfoDTO user = new UserInfoDTO();
			 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			 User currentUser = (User) authentication.getPrincipal();
			 user.setId(currentUser.getId());
			 user.setName(currentUser.getName());
			 user.setEmail(currentUser.getEmail());
			 user.setCreatedAt(currentUser.getCreatedAt());
			 user.setUpdatedAt(currentUser.getUpdatedAt());
			 
			 return user;
			 
		 } catch(Exception e) {
		     throw new CustomException("Error while fetching authenticated user data", HttpStatus.BAD_REQUEST);
		 }
	 }
	 
	 public User authenticate(User user) {			
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
			
		return userRepository.findByEmail(user.getEmail())
					.orElseThrow(() -> new CustomException("User not found", HttpStatus.BAD_REQUEST));			
      }
	  
	  
	 
	 	
	
	
}
