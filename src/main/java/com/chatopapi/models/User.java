package com.chatopapi.models;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {

	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Integer id;

	 private String email;

	 private String name;

	 private String password;

	 @CreationTimestamp
	 private LocalDateTime createdAt;

	 @UpdateTimestamp
	 private LocalDateTime updatedAt;

	 @OneToMany(mappedBy = "owner")
	 private List<Rental> rentals;
	
}
