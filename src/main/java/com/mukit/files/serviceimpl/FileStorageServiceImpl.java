package com.mukit.files.serviceimpl;

import com.mukit.files.model.FileInfo;
import com.mukit.files.service.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
@RequiredArgsConstructor
public class FileStorageServiceImpl implements FileStorageService {

    @Override
    public void storeFile(MultipartFile file, FileInfo fileInfo) throws IOException {
        Path directoryPath = Files.createDirectories(Paths.get(fileInfo.getFileLocation()));
        Path targetLocation = directoryPath.resolve(file.getOriginalFilename());
        Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
    }

    @Override
    public void storeFile(byte[] decodedData, FileInfo fileInfo) throws IOException {
        Path directoryPath = Files.createDirectories(Paths.get(fileInfo.getFileLocation()));
        Path targetLocation = directoryPath.resolve(fileInfo.getFileName());
        Files.write(targetLocation, decodedData);
    }

    @Override
    public Resource retrieveFileAsResource(FileInfo fileInfo) throws IOException {
        Path filePath = Paths.get(fileInfo.getFileLocation()).resolve(fileInfo.getFileName());
        Resource resource = new UrlResource(filePath.toUri());
        if (resource.exists())
            return resource;
        else
            throw new RuntimeException("File not found with location: " + fileInfo.getFileLocation() + fileInfo.getFileName());
    }

    @Override
    public void deleteFile(FileInfo fileInfo) {
        try {
            Path directoryPath = Files.createDirectories(Paths.get(fileInfo.getFileLocation()));
            Path targetLocation = directoryPath.resolve(fileInfo.getFileName());
            Files.deleteIfExists(targetLocation);
            Files.deleteIfExists(directoryPath);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
