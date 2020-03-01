package fr.test.java.modele;

import java.io.Serializable;

public class UploadFileDetail implements Serializable {

	private long fileSize;
	private String fileName, uploadStatus;

	private static final long serialVersionUID = 1L;

	public UploadFileDetail(String filename, long size) {
		this.fileName = filename;
		this.fileSize = size;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getUploadStatus() {
		return uploadStatus;
	}

	public void setUploadStatus(String uploadStatus) {
		this.uploadStatus = uploadStatus;
	}
}