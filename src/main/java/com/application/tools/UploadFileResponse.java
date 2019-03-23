package com.application.tools;

public class UploadFileResponse {

    public UploadFileResponse() {
    }

    public UploadFileResponse(String fileName, String fileDownloadUri, String fileType, long size) {
        this.fileName = fileName;
        this.fileDownloadUri = fileDownloadUri;
        this.fileType = fileType;
        this.size = size;
    }

    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long size;


    // Getters and Setters (Omitted for brevity)

    public String getFileName() {
        return fileName;
    }

    public UploadFileResponse setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public String getFileDownloadUri() {
        return fileDownloadUri;
    }

    public UploadFileResponse setFileDownloadUri(String fileDownloadUri) {
        this.fileDownloadUri = fileDownloadUri;
        return this;
    }

    public String getFileType() {
        return fileType;
    }

    public UploadFileResponse setFileType(String fileType) {
        this.fileType = fileType;
        return this;
    }

    public long getSize() {
        return size;
    }

    public UploadFileResponse setSize(long size) {
        this.size = size;
        return this;
    }
}