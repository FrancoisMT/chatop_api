package com.chatopapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class MessageDTO {
    @NotBlank(message = "message is required")
	private String message;
    @NotNull(message = "Rental ID is required")
	private Integer rental_id;
    @NotNull(message = "User ID is required")
	private Integer user_id;
	
	public String getMessage() {
		return this.message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public Integer getRental_id() {
		return this.rental_id;
	}
	
	public void setRentalId(Integer rentalId) {
		this.rental_id = rentalId;
	}
	
	public Integer getUser_id() {
		return this.user_id;
	}
	
	public void setUserId(Integer userId) {
		this.user_id = userId;
	}
	
}
