package com.mukit.files.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class FileUploadRequest {
    @NotBlank
    private String fileName;
    @NotBlank
    private String mimeType;
    @NotBlank
    private String extension;
    @NotBlank
    private String base64Data;
}
