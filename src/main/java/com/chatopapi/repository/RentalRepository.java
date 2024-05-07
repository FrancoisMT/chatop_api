package com.chatopapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chatopapi.models.Rental;

@Repository
public interface RentalRepository extends JpaRepository<Rental, String> {

	
}
