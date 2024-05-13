package com.chatopapi.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.chatopapi.dto.MessageDTO;
import com.chatopapi.models.Message;
import com.chatopapi.models.Rental;
import com.chatopapi.models.User;
import com.chatopapi.response.MessageResponseHandler;
import com.chatopapi.services.MessageService;
import com.chatopapi.services.RentalService;

import jakarta.validation.Valid;

@RestController
public class MessageController {
	private final MessageService messageService;
	private final RentalService rentalService;
	
	public MessageController(MessageService messageService, RentalService rentalService) {
		this.messageService = messageService;
		this.rentalService = rentalService;
	}
	
	@PostMapping(path = "/messages")
	public ResponseEntity<Object> create(@RequestBody @Valid MessageDTO messageDTO) throws Exception {	
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User currentUser = (User) authentication.getPrincipal();
			
		Rental rental = rentalService.getRentalById(messageDTO.getRental_id().toString());
			
		Message message = new Message();
		message.setMessage(messageDTO.getMessage());
		message.setRental(rental);
		message.setUser(currentUser);
		
		messageService.createMessage(message);
			
		return ResponseEntity.ok().body(new MessageResponseHandler("Message send with success")); 	
	}

}
