package com.chatopapi.services;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.chatopapi.dto.RentalDTO;
import com.chatopapi.dto.RentalsDTO;
import com.chatopapi.exceptions.CustomException;
import com.chatopapi.models.Rental;
import com.chatopapi.repository.RentalRepository;


@Service
public class RentalService {
	private final RentalRepository rentalRepository;
	private final MediaService mediaService;
	
	public RentalService(RentalRepository rentalRepository, MediaService mediaService) {
		this.rentalRepository = rentalRepository;
		this.mediaService = mediaService;
	}
	
	
	public void createRental(Rental rental, MultipartFile picture) {
		
		try {
			
			String pictureUrl = mediaService.storePicture(picture);
			rental.setPicture(pictureUrl);
			rentalRepository.save(rental);
			
		} catch (Exception e) {
			throw new CustomException("Error while creating rental data", HttpStatus.BAD_REQUEST);
		}
		
	 }
	
	public void updateRental(Rental rental) {
		rentalRepository.save(rental);
	}
	
	public Rental getRentalById(String id) {
        Optional<Rental> rentalOptional = rentalRepository.findById(id);

        if (rentalOptional.isPresent()) {
            return rentalOptional.get();
        } else {
            throw new CustomException("Rental not found", HttpStatus.NOT_FOUND);
        }
    }
	
	public RentalsDTO getAll() {
		List<Rental> rentals = rentalRepository.findAll();
		
		List<RentalDTO> list = rentals.stream()
                .map(this::convertToDTO) 
                .collect(Collectors.toList());
		
		RentalsDTO rentalsDTO = new RentalsDTO(list);
		
		return rentalsDTO;
	}
	
	public RentalDTO get(String id) {
		Optional<Rental> rental = rentalRepository.findById(id);
		
		if (!rental.isPresent()) {
			throw new CustomException("Rental not found", HttpStatus.NOT_FOUND);
		}
		
		return convertToDTO(rental.get());
	}
		
	private RentalDTO convertToDTO(Rental rental) {
        RentalDTO rentalDTO = new RentalDTO();
        rentalDTO.setId(rental.getId());
        rentalDTO.setName(rental.getName());
        rentalDTO.setSurface(rental.getSurface());
        rentalDTO.setPrice(rental.getPrice());
        rentalDTO.setPicture(rental.getPicture());
        rentalDTO.setDescription(rental.getDescription());
        rentalDTO.setOwner_id(rental.getOwner().getId());
        rentalDTO.setCreated_at(rental.getCreatedAt());
        rentalDTO.setUpdated_at(rental.getUpdatedAt());
        return rentalDTO;
    }
	
	
}
