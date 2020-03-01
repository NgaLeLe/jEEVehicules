package fr.controlleur;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.service.TreeFiles;
import fr.test.java.modele.InfosFile;
import fr.test.java.modele.NodeFile;
import fr.test.java.modele.UploadFileDetail;

public class FilesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		TreeFiles tree = new TreeFiles();
		ArrayList<InfosFile> fileList = new ArrayList<InfosFile>();

		String uploadDir = "C://Uploadfiles";
		File treeDirectory = new File(uploadDir);
		tree.fileTreeDirectory(fileList, treeDirectory, 0);

		// if(file.isDirectory())

		// xem them ve cau truc dang cay html
		// https://www.w3schools.com/howto/howto_js_treeview.asp

		request.setAttribute("files", fileList);
		this.getServletContext().getRequestDispatcher("/viewJSP/files.jsp").forward(request, response);
	}
}