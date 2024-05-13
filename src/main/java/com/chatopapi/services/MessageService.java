package com.chatopapi.services;

import org.springframework.stereotype.Service;
import com.chatopapi.models.Message;
import com.chatopapi.repository.MessageRepository;

@Service
public class MessageService {
	private final MessageRepository messageRepository; 
	
	public MessageService(MessageRepository messageRepository) {
		this.messageRepository = messageRepository;
	}
	
	public void createMessage(Message message) {
		messageRepository.save(message);	
	}
		
}
