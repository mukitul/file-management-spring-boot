package com.bezkoder.spring.files.upload.service;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.bezkoder.spring.files.upload.model.FileInfo;

public interface FilesStorageService {
	public void init();

	public FileInfo save(MultipartFile file);

	public Resource load(String filename);

	public void deleteAll();

	public Stream<Path> loadAll();
}
