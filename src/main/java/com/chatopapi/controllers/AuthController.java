package com.chatopapi.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chatopapi.dto.JwtResponseDTO;
import com.chatopapi.dto.UserDTO;
import com.chatopapi.dto.UserInfoDTO;
import com.chatopapi.models.User;
import com.chatopapi.services.AuthService;
import com.chatopapi.services.JWTService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
	 private final JWTService jwtService;
	 private final AuthService authService;
	 
	 public AuthController(JWTService jwtService, AuthService authService) {
	        this.jwtService = jwtService;
	        this.authService = authService;
	 }
		
	 @PostMapping("/register")
	 public ResponseEntity<JwtResponseDTO> register(@RequestBody @Valid UserDTO userDTO) throws Exception {
	     User newUser = authService.registerUser(userDTO);	     
	     String jwtToken = jwtService.generateToken(newUser); 

	     return ResponseEntity.ok(new JwtResponseDTO(jwtToken));
	 }
	 
	 @PostMapping("/login")
	 public ResponseEntity<JwtResponseDTO> login(@RequestBody @Valid UserDTO userDTO) throws Exception {
		 User authenticatedUser = authService.login(userDTO);	
		 String jwtToken = jwtService.generateToken(authenticatedUser);
		 
	     return ResponseEntity.ok(new JwtResponseDTO(jwtToken));
	 }
	 
	 @GetMapping("/me")
	 public ResponseEntity<UserInfoDTO> authenticatedUser() {
		 UserInfoDTO user = authService.authenticatedUser();
			 
		 return ResponseEntity.ok(user);
	 } 
	 
	 
	 
	 
	
}
