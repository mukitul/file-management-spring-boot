package com.mukit.files.repository;


import com.mukit.files.model.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileInfoRepository extends JpaRepository<FileInfo, Long> {
    FileInfo findByResourceId(String resourceId);
}
