package fr.test.java.modele;

import java.io.Serializable;

public class InfosFile implements Serializable {
	private long fileSize;
	private String fileName, nameParent, uploadStatus;
	private int level;
	private boolean dossier = false;

	private static final long serialVersionUID = 1L;

	public InfosFile() {
	}

	public InfosFile(String fileName, String nameParent, int level, long fileSize) {
		this.fileSize = fileSize;
		this.fileName = fileName;
		this.nameParent = nameParent;
		this.level = level;
	}

	public InfosFile(String fileName, String nameParent, long fileSize, String uploadStatus) {
		this.fileSize = fileSize;
		this.fileName = fileName;
		this.nameParent = nameParent;
		this.uploadStatus = uploadStatus;
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

	public String getNameParent() {
		return nameParent;
	}

	public void setNameParent(String nameParent) {
		this.nameParent = nameParent;
	}

	public String getUploadStatus() {
		return uploadStatus;
	}

	public void setUploadStatus(String uploadStatus) {
		this.uploadStatus = uploadStatus;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public boolean isDossier() {
		return dossier;
	}

	public void setDossier() {
		this.dossier = true;
	}

}
