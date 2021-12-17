package com.mukit.files.controller;

import com.mukit.files.dto.request.wrapper.FileUploadRequestDto;
import com.mukit.files.dto.response.FileDownloadResponse;
import com.mukit.files.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;

    @PostMapping("/upload/multipart-file")
    public ResponseEntity<?> uploadMultipartFile(@RequestParam("file") MultipartFile file) {
        return new ResponseEntity<>(fileService.uploadMultipartFile(file), HttpStatus.OK);
    }

    @PostMapping("/upload/encoded-file")
    public ResponseEntity<?> uploadMultipartFile(@RequestBody @Valid FileUploadRequestDto requestDto) {
        return new ResponseEntity<>(fileService.uploadEncodedFile(requestDto), HttpStatus.OK);
    }

    @GetMapping("/download/{resourceId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String resourceId) {
        FileDownloadResponse fileDownloadResponse = fileService.downloadResource(resourceId);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(fileDownloadResponse.getMimeType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDownloadResponse.getFileName() + "\"")
                .body(fileDownloadResponse.getResource());
    }

    @GetMapping("/download/encoded-file/{resourceId}")
    public ResponseEntity<?> downloadEncodedFile(@PathVariable String resourceId) {
        return new ResponseEntity<>(fileService.downloadEncodedFile(resourceId), HttpStatus.OK);
    }
}
