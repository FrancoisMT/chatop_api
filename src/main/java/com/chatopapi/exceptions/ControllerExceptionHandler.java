package com.chatopapi.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;

@Order(2)
@RestControllerAdvice
public class ControllerExceptionHandler {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
    public ResponseEntity<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        String errorMessage = "Invalid request body: " + e.getLocalizedMessage();
    	 
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorMessage);        
    }
	
	 @ResponseStatus(HttpStatus.BAD_REQUEST)
	 @ExceptionHandler(MethodArgumentNotValidException.class)
	 public Map<String, Object> handleInvalidArgument(MethodArgumentNotValidException ex) {
	   Map<String, Object> errorMap = new HashMap<>();
	       
	   for (FieldError error : ex.getBindingResult().getFieldErrors()) {
	         errorMap.put(error.getField(), error.getDefaultMessage());
	   }
	        
	   errorMap.put("status", HttpStatus.BAD_REQUEST);
	        
	   return errorMap;
	}
	 
	 @ExceptionHandler(CustomException.class) 
     public ResponseEntity<Object> handleCustomException(CustomException ex) {
	        var response = new HashMap<String, Object>();
	        response.put("message", ex.getMessage());
	        response.put("status", ex.getHttpStatus().value());
	        return ResponseEntity
	                .status(ex.getHttpStatus()) 
	                .body(response);
	 }
	 
	 @ExceptionHandler(ExpiredJwtException.class)
	 public ResponseEntity<String> handleExpiredJwtException(ExpiredJwtException ex) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
	                             .body("Token has expired.");
	 }

	 @ExceptionHandler({ SignatureException.class, MalformedJwtException.class })
	 public ResponseEntity<String> handleSignatureException(Exception ex) {
	      return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
	                        .body("Invalid JWT");
	 }


	
}