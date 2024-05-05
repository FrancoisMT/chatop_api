package com.chatopapi.models;

import java.math.BigDecimal;
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
import lombok.Data;

@Data
@Entity
@Table(name = "rentals")
public class Rental {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Integer id;

	 private String name;

	 private BigDecimal surface;

	 private BigDecimal price;

	 private String picture;

	 private String description;

	 @Column(name="created_at")
	 @CreationTimestamp
	 private LocalDateTime createdAt;
	 
	 @Column(name="updated_at")
	 @UpdateTimestamp
	 private LocalDateTime updatedAt;

	 @ManyToOne	 
	 @JoinColumn(name="owner_id")
	 private User owner;

}
