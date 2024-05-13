package com.chatopapi.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chatopapi.dto.JwtResponseDTO;
import com.chatopapi.dto.LoginRequestDTO;
import com.chatopapi.dto.UserDTO;
import com.chatopapi.dto.UserInfoDTO;
import com.chatopapi.models.User;
import com.chatopapi.services.AuthService;
import com.chatopapi.services.JWTService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
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
	 @Operation(summary = "Register a new user", description = "This operation registers a new user and returns a jwt token.")
	 @ApiResponses(value = {
	     @ApiResponse(responseCode = "200", description = "User successfully registered", 
	    		 content = @Content(mediaType = "application/json",
                 schema = @Schema(implementation = JwtResponseDTO.class))),
	     @ApiResponse(responseCode = "409", description = "Email already exists", content = @Content),
	     @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
	 })
	 public ResponseEntity<JwtResponseDTO> register(@RequestBody @Valid UserDTO userDTO) throws Exception {
	     User newUser = authService.registerUser(userDTO);	     
	     String jwtToken = jwtService.generateToken(newUser); 

	     return ResponseEntity.ok(new JwtResponseDTO(jwtToken));
	 }
	 
	 @PostMapping("/login")
	 @Operation(summary = "Log in a user", description = "This operation logs in a user and returns a jwt token.")
	    @ApiResponses(value = { 
	        @ApiResponse(responseCode = "200", description = "User logged in successfully", 
	        		content = @Content(mediaType = "application/json",
	                 schema = @Schema(implementation = JwtResponseDTO.class))),
	        @ApiResponse(responseCode = "400", description = "Invalid login credentials", content = @Content), 
	        @ApiResponse(responseCode = "404", description = "User not found", content = @Content), 
	 })
	 public ResponseEntity<JwtResponseDTO> login(@RequestBody @Valid LoginRequestDTO request) {
		 User authenticatedUser = authService.login(request);	
		 String jwtToken = jwtService.generateToken(authenticatedUser);
		 
	     return ResponseEntity.ok(new JwtResponseDTO(jwtToken));
	 }
	 
	 @GetMapping("/me")
	 @Operation(summary = "Get current user", description = "This operation returns the details of the currently authenticated user.")
	    @ApiResponses(value = { 
	        @ApiResponse(responseCode = "200", description = "User details retrieved successfully", 
	        		content = @Content(mediaType = "application/json",
	                 schema = @Schema(implementation = UserInfoDTO.class))),
	        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
	        @ApiResponse(responseCode = "401", description = "Invalid token", content = @Content), 
	        @ApiResponse(responseCode = "403", description = "Unauthorized", content = @Content), 
	 })
	 public ResponseEntity<UserInfoDTO> authenticatedUser() {
		 UserInfoDTO user = authService.authenticatedUser();
		 	 
		 return ResponseEntity.ok(user);
	 } 
	 
	 
	 
	 
	
}
