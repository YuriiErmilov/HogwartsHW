package ru.hogwarts.school.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Avatar {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String filePath;
    private long fileSize;
    private String mediaType;


    public Long getId() {
        return id;
    }

    public String getFilePath() {
        return filePath;
    }

    public long getFileSize() {
        return fileSize;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }
}
