package com.mukit.files.service;

import com.mukit.files.dto.request.wrapper.FileUploadRequestDto;
import com.mukit.files.dto.response.FileDownloadResponse;
import com.mukit.files.dto.response.FileUploadResponseDto;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    FileUploadResponseDto uploadMultipartFile(MultipartFile file);

    FileUploadResponseDto uploadEncodedFile(FileUploadRequestDto requestDto);

    FileDownloadResponse downloadResource(String resourceId);

    FileDownloadResponse downloadEncodedFile(String resourceId);
}
