package com.chatopapi.models;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "users")
public class User implements UserDetails {

	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Integer id;

	 @Column(nullable = false)
	 private String email;

	 @Column(nullable = false)
	 private String name;

	 @Column(nullable = false)
	 private String password;

	 @CreationTimestamp
	 private LocalDateTime createdAt;

	 @UpdateTimestamp
	 private LocalDateTime updatedAt;

	 @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER)
	 private List<Rental> rentals;
	 
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
	 
	 public void setPassword(String password) {
		 this.password = password;
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

	 public List<Rental> getRentals() {
	    return rentals;
	 }
	
	 public void setRentals(List<Rental> rentals) {
	     this.rentals = rentals;
	 }
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of();
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		 return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
}
