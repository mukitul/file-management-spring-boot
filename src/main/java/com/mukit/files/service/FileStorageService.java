package com.mukit.files.service;

import com.mukit.files.model.FileInfo;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileStorageService {
    void storeFile(MultipartFile file, FileInfo fileInfo) throws IOException;

    void storeFile(byte[] decodedData, FileInfo fileInfo) throws IOException;

    Resource retrieveFileAsResource(FileInfo fileInfo) throws IOException;

    void deleteFile(FileInfo fileInfo);
}
