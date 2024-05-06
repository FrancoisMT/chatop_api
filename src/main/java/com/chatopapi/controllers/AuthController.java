package com.chatopapi.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chatopapi.dto.JwtResponseDTO;
import com.chatopapi.dto.UserDTO;

@RestController
@RequestMapping("/auth")
public class AuthController {

	 @PostMapping("/register")
	 public ResponseEntity<JwtResponseDTO> registerUser(@RequestBody UserDTO userDTO) {
	        return ResponseEntity.ok(new JwtResponseDTO("exampleToken"));
	 }
	
}
