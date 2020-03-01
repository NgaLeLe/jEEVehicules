package fr.service;

import java.io.File;
import java.util.ArrayList;

import com.sun.source.tree.Tree;

import fr.test.java.modele.InfosFile;
import fr.test.java.modele.NodeFile;

public class TreeFiles {

//	public NodeFile fileTreeDirectory(File file, int level) {
//		int positionSlache = file.getParent().lastIndexOf("\\");
//		String tmpNameParent = file.getParent().substring(positionSlache + 1);
//		if (file.isDirectory()) { // c'est un dossier
//			InfosFile fileInfo = new InfosFile(file.getName(), tmpNameParent, level, file.length());
//			fileInfo.setDossier();
//			NodeFile nodeFile = new NodeFile(file.getName(), fileInfo);
//			// System.out.println(file.getName());
//			File[] children = file.listFiles();
//			nodeFile.subNodes = new ArrayList<NodeFile>();
//			for (File child : children) {
//				NodeFile x = this.fileTreeDirectory(child, level + 1); // rescurssive
//				nodeFile.subNodes.add(x);
//			}
//			return nodeFile;
//		} else {
//			InfosFile fileInfo = new InfosFile(file.getName(), tmpNameParent, level, file.length() / 1024);
//			// System.out.println(file.getName());
//			NodeFile x = new NodeFile(file.getName(), fileInfo);
//			return x;
//		}
//	}

	public void fileTreeDirectory(ArrayList<InfosFile> fileList, File file, int level) {
		int positionSlache = file.getParent().lastIndexOf("\\");
		String tmpNameParent = file.getParent().substring(positionSlache + 1);

		if (file.isDirectory()) { // c'est un dossier
			InfosFile fileInfo = new InfosFile(file.getName(), tmpNameParent, level, file.length() / 1024);
			fileInfo.setDossier();

			fileList.add(fileInfo);
			File[] children = file.listFiles();
			for (File child : children) {
				this.fileTreeDirectory(fileList, child, level + 1); // rescurssive
			}
		} else {
			InfosFile fileInfo = new InfosFile(file.getName(), tmpNameParent, level, file.length() / 1024);
			fileList.add(fileInfo);
		}

	}

//	public void printNodeFile(NodeFile nodeFile) {
//		System.out.println(nodeFile.name + "--" + nodeFile.nodeInfo.isDossier() + "--" + nodeFile.nodeInfo.getLevel()
//				+ "--" + nodeFile.nodeInfo.getFileName());
//		if (nodeFile.subNodes != null && (!nodeFile.subNodes.isEmpty())) {
//			for (NodeFile subNode : nodeFile.subNodes) {
//				printNodeFile(subNode);
//			}
//		}
//	}

	public static void main(String[] args) {
		TreeFiles tree = new TreeFiles();

		String uploadDir = "C:\\Uploadfiles";
		File treeDirectory = new File(uploadDir);
		ArrayList<InfosFile> fileList = new ArrayList<InfosFile>();
		tree.fileTreeDirectory(fileList, treeDirectory, 0);

//		NodeFile aNodeFile = tree.fileTreeDirectory(treeDirectory, 0);
//		tree.printNodeFile(aNodeFile);

		for (InfosFile f : fileList) {
			System.out.println(f.getNameParent() + "--" + f.getLevel() + "--" + f.isDossier() + "--" + f.getFileName());
		}
	}

}
