package com.mukit.files.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mukit.files.model.FileInfo;

public interface FileDetailRepository extends MongoRepository<FileInfo, String> {

}
