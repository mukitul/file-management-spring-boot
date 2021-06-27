package com.bezkoder.spring.files.upload.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bezkoder.spring.files.upload.model.FileInfo;

public interface FileDetailRepository extends MongoRepository<FileInfo, String> {

}
