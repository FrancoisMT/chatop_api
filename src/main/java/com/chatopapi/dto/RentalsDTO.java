package com.chatopapi.dto;

import java.util.List;

public class RentalsDTO {
	private List<RentalDTO> rentals;
	
	public RentalsDTO(List<RentalDTO> rentals) {
		this.rentals = rentals;
	}
	
	public List<RentalDTO> getRentals() {
		return this.rentals;
	}
	
	public void setRentals(List<RentalDTO> rentals) {
		this.rentals = rentals;
	}

}
