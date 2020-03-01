package fr.test.java.modele;

import java.util.ArrayList;

public class NodeFile {
	private Integer id;
	private long size;
	private String fileName;
	private String status;
	private ArrayList<NodeFile> subNodes;

	public NodeFile() {

	}

	public NodeFile(String name) {
		this.fileName = name;
		this.id++;
	}

	public String getFileName() {
		return fileName;

	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public ArrayList<NodeFile> getSubNodes() {
		return subNodes;

	}

	public void setSubNodes(ArrayList<NodeFile> subNodes) {
		this.subNodes = subNodes;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
