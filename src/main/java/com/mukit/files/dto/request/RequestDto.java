package com.mukit.files.dto.request;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class RequestDto {
    private String requestId;
    private Date requestTime;

    public RequestDto() {
        this.requestId = UUID.randomUUID().toString();
        this.requestTime = new Date();
    }
}
