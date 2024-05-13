package com.chatopapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chatopapi.models.Message;

public interface MessageRepository extends JpaRepository<Message, String> {

}
