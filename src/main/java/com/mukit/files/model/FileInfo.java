package com.mukit.files.model;

import lombok.Data;

import javax.persistence.*;
import java.net.InetAddress;
import java.util.Date;

@Entity
@Table(name = "FILE_INFO")
@Data
public class FileInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "FILE_RESOURCE_ID")
    private String resourceId;
    @Column(name = "FILE_NAME")
    private String fileName;
    @Column(name = "FILE_MIME_TYPE")
    private String mimeType;
    @Column(name = "FILE_EXTENSION")
    private String extension;
    @Column(name = "FILE_SIZE")
    private String size;
    @Column(name = "FILE_LOCATION")
    private String fileLocation;
    @Column(name = "FILE_SERVER_IP")
    private String fileServerIp;
    @Column(name = "CREATED_AT")
    private Date createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = new Date();
    }

    public FileInfo() {
        try {
            this.fileServerIp = InetAddress.getLocalHost().getHostAddress();
        } catch (Exception e) {
            this.fileServerIp = "UNKNOWN";
        }
    }
}
