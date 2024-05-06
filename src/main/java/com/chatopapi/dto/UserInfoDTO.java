package com.chatopapi.dto;

import java.time.LocalDateTime;

public class UserInfoDTO {
	private Integer id;
	private String name;
    private String email;
    private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	 
	public Integer getId() {
	    return id;
	}
		 
	public void setId(Integer id) {
	    this.id = id;
	}	
	
	public String getEmail() {
	    return email;
	}
		 
	public void setEmail(String email) {
	   this.email = email;
	}
		 
	public String getName() {
	  return this.name;
	}
		 
	public void setName(String name) {
	    this.name = name;
	}
	
	public LocalDateTime getCreatedAt() {
	    return createdAt;
	}
	
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	 }
	
	 public LocalDateTime getUpdatedAt() {
	    return updatedAt;
	 }
	
	 public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	 } 
}
