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

@Entity
@Table(name = "rentals")
public class Rental {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Integer id;

	 @Column(nullable = false)
	 private String name;

	 @Column(nullable = false)
	 private BigDecimal surface;

	 @Column(nullable = false)
	 private BigDecimal price;
	 
	 @Column(nullable = false)
	 private String picture;
	 
	 @Column(nullable = false)
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
	 

     public Integer getId() {
         return id;
     }

     public String getName() {
        return name;
     }

     public BigDecimal getSurface() {
        return surface;
     }

     public BigDecimal getPrice() {
	    return price;
	 }

	 public String getPicture() {
	    return picture;
	 }

	 public String getDescription() {
	    return description;
	 }

	 public LocalDateTime getCreatedAt() {
	     return createdAt;
	 }

	 public LocalDateTime getUpdatedAt() {
	     return updatedAt;
	 }

	 public User getOwner() {
	     return owner;
	 }

	 public void setId(Integer id) {
	     this.id = id;
	 }

	 public void setName(String name) {
		 this.name = name;
     }

     public void setSurface(BigDecimal surface) {
         this.surface = surface;
     }

     public void setPrice(BigDecimal price) {
    	 this.price = price;
	 }

     public void setPicture(String picture) { 
    	 this.picture = picture;
	 }

     public void setDescription(String description) {
         this.description = description;
     }

     public void setOwner(User owner) {
         this.owner = owner;
     }

}
