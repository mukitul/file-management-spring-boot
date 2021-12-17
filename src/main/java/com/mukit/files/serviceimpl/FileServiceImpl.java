package com.mukit.files.serviceimpl;

import com.mukit.files.dto.request.wrapper.FileUploadRequestDto;
import com.mukit.files.dto.response.FileDownloadResponse;
import com.mukit.files.dto.response.FileUploadResponseDto;
import com.mukit.files.model.FileInfo;
import com.mukit.files.repository.FileInfoRepository;
import com.mukit.files.service.FileService;
import com.mukit.files.service.FileStorageService;
import com.mukit.files.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.modelmapper.ModelMapper;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import java.util.Base64;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    private final FileInfoRepository fileInfoRepository;
    private final FileStorageService fileStorageService;
    private final ModelMapper modelMapper;
    private final Base64.Encoder base64Encoder = Base64.getEncoder();
    private final BASE64Decoder base64Decoder = new BASE64Decoder();

    @Override
    public FileUploadResponseDto uploadMultipartFile(MultipartFile file) {
        FileUploadResponseDto responseDto = new FileUploadResponseDto();
        FileInfo fileInfo = new FileInfo();
        try {
            if (StringUtils.isEmpty(FilenameUtils.getExtension(file.getOriginalFilename()))) {
                throw new RuntimeException("Invalid File Name");
            }
            setFileInfo(file.getBytes(), null, file, fileInfo);
            fileStorageService.storeFile(file, fileInfo);
            fileInfoRepository.save(fileInfo);
            responseDto.setResourceId(fileInfo.getResourceId());
        } catch (Exception e) {
            fileStorageService.deleteFile(fileInfo);
        }
        return responseDto;
    }

    @Override
    public FileUploadResponseDto uploadEncodedFile(FileUploadRequestDto requestDto) {
        FileUploadResponseDto responseDto = new FileUploadResponseDto();
        FileInfo fileInfo = new FileInfo();
        try {
            byte[] decodedData = base64Decoder.decodeBuffer(requestDto.getFileUploadRequest().getBase64Data());
            setFileInfo(decodedData, requestDto, null, fileInfo);
            fileStorageService.storeFile(decodedData, fileInfo);
            fileInfoRepository.save(fileInfo);
            responseDto.setResourceId(fileInfo.getResourceId());
        } catch (Exception e) {
            fileStorageService.deleteFile(fileInfo);
        }
        return responseDto;
    }

    @Override
    public FileDownloadResponse downloadResource(String resourceId) {
        FileDownloadResponse fileDownloadResponse = new FileDownloadResponse();
        try {
            FileInfo fileInfo = fileInfoRepository.findByResourceId(resourceId);
            Resource resource = fileStorageService.retrieveFileAsResource(fileInfo);
            modelMapper.map(fileInfo, fileDownloadResponse);
            fileDownloadResponse.setResource(resource);
        } catch (Exception e) {
            fileDownloadResponse = null;
        }
        return fileDownloadResponse;
    }

    @Override
    public FileDownloadResponse downloadEncodedFile(String resourceId) {
        FileDownloadResponse fileDownloadResponse = new FileDownloadResponse();
        try {
            FileInfo fileInfo = fileInfoRepository.findByResourceId(resourceId);
            Resource resource = fileStorageService.retrieveFileAsResource(fileInfo);
            byte[] byteData = FileUtils.readFileToByteArray(resource.getFile());
            modelMapper.map(fileInfo, fileDownloadResponse);
            fileDownloadResponse.setBase64Data(base64Encoder.encodeToString(byteData));
        } catch (Exception e) {
            fileDownloadResponse = null;
        }
        return fileDownloadResponse;
    }

    private void setFileInfo(byte[] byteData, FileUploadRequestDto requestDto, MultipartFile multipartFile, FileInfo fileInfo) {
        if (null != requestDto) {
            modelMapper.map(requestDto.getFileUploadRequest(), fileInfo);
        } else if (null != multipartFile) {
            fileInfo.setFileName(multipartFile.getOriginalFilename());
            fileInfo.setExtension(FilenameUtils.getExtension(fileInfo.getFileName()));
            fileInfo.setMimeType(multipartFile.getContentType());
        }
        fileInfo.setResourceId(UUID.randomUUID().toString());
        fileInfo.setFileLocation(createPath(fileInfo.getResourceId()));
        fileInfo.setSize(byteData.length / 1024 + " KB");
    }

    private String createPath(String resourceId) {
        return Constants.BASE_PATH + "/" + resourceId + "/";
    }
}
