package com.chatopapi.controllers;

import java.math.BigDecimal;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.chatopapi.dto.RentalDTO;
import com.chatopapi.dto.RentalsDTO;
import com.chatopapi.models.Rental;
import com.chatopapi.models.User;
import com.chatopapi.response.MessageResponseHandler;
import com.chatopapi.services.RentalService;

import jakarta.validation.constraints.NotNull;

@RestController
public class RentalController {
	private final RentalService rentalService;
	
	public RentalController(RentalService rentalService) {
		this.rentalService = rentalService;
	}

	@PostMapping(path = "/rentals", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<Object> create( 
			@RequestPart("name") @NotNull String name,
	        @RequestPart("surface") @NotNull String surface,
	        @RequestPart("price") @NotNull String price,
	        @RequestPart("picture") @NotNull MultipartFile picture,
	        @RequestPart("description") @NotNull String description
	 ) {
		
		if (picture.isEmpty()) {
            return ResponseEntity.badRequest().body("No image provided");
        }
		
		if (!picture.getContentType().startsWith("image/")) {
            return ResponseEntity.badRequest().body("File is not an image");
        }
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();

        Rental rental = new Rental();
        rental.setName(name);
        rental.setSurface(new BigDecimal(surface));
        rental.setPrice(new BigDecimal(price));
        rental.setDescription(description);
        rental.setOwner(currentUser);
				
		rentalService.createRental(rental, picture);
        
		return ResponseEntity.ok().body(new MessageResponseHandler("Rental created !"));
	}
	
	@PutMapping(path = "/rentals/{id}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<Object> update( 
			@PathVariable("id") @NotNull String id,
            @RequestPart("name") @NotNull String name,
            @RequestPart("surface") @NotNull String surface,
            @RequestPart("price") @NotNull String price,
            @RequestPart("description") @NotNull String description ) {
		
        Rental existingRental  = rentalService.getRentalById(id);
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        
        if (!existingRental.getOwner().getId().equals(currentUser.getId())) {
            return ResponseEntity.status(403).body("You are not authorized to modify this rental");
        }
        
        existingRental.setName(name);
        existingRental.setSurface(new BigDecimal(surface));
        existingRental.setPrice(new BigDecimal(price));
        existingRental.setDescription(description);
        
        rentalService.updateRental(existingRental);
		
		return ResponseEntity.ok().body(new MessageResponseHandler("Rental updated !"));
	}
	
	@GetMapping(path="/rentals")
	public ResponseEntity<RentalsDTO> getAll() {
		RentalsDTO list = rentalService.getAll();
		
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(path="/rentals/{id}")
	public ResponseEntity<RentalDTO> get(@PathVariable("id") @NotNull String id) {
		RentalDTO rentalDTO = rentalService.get(id);
			
		return ResponseEntity.ok().body(rentalDTO);
	}
	
}
