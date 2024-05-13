package com.chatopapi.services;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.chatopapi.exceptions.CustomException;

@Service
public class MediaService {
	
		@Value("${image.upload-dir}")
		private String uploadDir;

		public String storePicture(MultipartFile picture) {
		
			try {
			
				String filename = StringUtils.cleanPath(picture.getOriginalFilename());
				String uniqueFilename = generateUniqueFilename(filename);
			
				Path uploadPath = Paths.get(uploadDir); 
				
				if (!Files.exists(uploadPath)) {
					Files.createDirectories(uploadPath);
				}
			
				Path targetLocation = uploadPath.resolve(uniqueFilename);
				Files.copy(picture.getInputStream(), targetLocation);
			
				return targetLocation.toString();
			
			} catch (Exception e) {
				throw new CustomException("Error while storing picture", HttpStatus.BAD_REQUEST);
			}
		
		}
		
		private String generateUniqueFilename(String filename) {
			
		    LocalDateTime now = LocalDateTime.now();
		    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
		    String timestamp = now.format(formatter);
		    
		    String extension = filename.substring(filename.lastIndexOf('.'));
		    String nameWithoutExtension = filename.substring(0, filename.lastIndexOf('.'));
		    
		    return nameWithoutExtension + "_" + timestamp + extension;
		}

		
}
