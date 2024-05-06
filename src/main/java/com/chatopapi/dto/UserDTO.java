package com.chatopapi.dto;


import jakarta.validation.constraints.NotNull;

public class UserDTO {
	private String name;
    private String email;
    private String password;
	
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
		this.name = name;
	}

    @NotNull(message = "email cannot be NULL")
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
	    this.email = email;
	}

    @NotNull(message = "password cannot be NULL")
    public String getPassword() {
        return this.password;
    }
    
}
