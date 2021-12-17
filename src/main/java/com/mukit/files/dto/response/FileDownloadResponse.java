package com.mukit.files.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.core.io.Resource;

@Data
public class FileDownloadResponse {
    private String resourceId;
    private String fileName;
    private String mimeType;
    private String extension;
    private String base64Data;
    private String size;
    @JsonIgnore
    private Resource resource;
}
