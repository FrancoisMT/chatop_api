package com.chatopapi.models;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "messages")
public class Message {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Integer id;
	 
	 @Column(length = 2000, nullable = false)
	 private String message;
	
	 @CreationTimestamp
	 private LocalDateTime createdAt;

	 @UpdateTimestamp
	 private LocalDateTime updatedAt;
	
	 @ManyToOne
	 @JoinColumn(name = "rental_id", nullable = false)
	 private Rental rental;

	 @ManyToOne
	 @JoinColumn(name = "user_id", nullable = false)
	 private User user;
	 
	 public Integer getId() {
	      return id;
	 }

	 public void setId(Integer id) {
	      this.id = id;
	 }

	 public Rental getRental() {
	      return rental;
	 }

	 public void setRental(Rental rental) {
	      this.rental = rental;
	 }

	 public User getUser() {
	      return user;
	 }

	 public void setUser(User user) {
		 this.user = user;
	 }

	 public String getMessage() {
	     return message;
	 }

	 public void setMessage(String message) {
	     this.message = message;
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
