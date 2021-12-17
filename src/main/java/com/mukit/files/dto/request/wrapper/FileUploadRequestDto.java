package com.mukit.files.dto.request.wrapper;

import com.mukit.files.dto.request.FileUploadRequest;
import com.mukit.files.dto.request.RequestDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
public class FileUploadRequestDto extends RequestDto {
    @NotNull
    @Valid
    private FileUploadRequest fileUploadRequest;
}
