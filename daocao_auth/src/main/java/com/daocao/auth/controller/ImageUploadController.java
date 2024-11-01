package com.daocao.auth.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
@RequestMapping("/api/upload")
public class ImageUploadController {
    @PostMapping
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            // Generate a unique filename to prevent overwriting existing files
            String originalFilename = file.getOriginalFilename();
            String fileName = UUID.randomUUID().toString() + "_" + originalFilename;
            String filePath = "C:\\Users\\ASUS\\Pictures\\Dys" ;
            // Define the directory where the file will be saved
            Path uploadDir = Paths.get(filePath);

            // Create the directory if it does not exist
            Files.createDirectories(uploadDir);

            // Save the file
            Files.copy(file.getInputStream(), uploadDir.resolve(fileName));

            return ResponseEntity.ok(filePath+'\\'+ fileName);
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Error occurred while uploading file");
        }
    }

}
