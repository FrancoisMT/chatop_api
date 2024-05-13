package com.chatopapi.dto;

public class LoginRequestDTO {
	private String email;
	private String password;
	
	public LoginRequestDTO(String email, String password) {
		this.email= email;
		this.password = password;
	}
	
	public String getEmail() {
		return this.email;
	}
		
	public String getPassword() {
		return this.password;
	}
}
