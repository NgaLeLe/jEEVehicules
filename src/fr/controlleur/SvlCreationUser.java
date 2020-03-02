package fr.controlleur;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.service.UtilisateurDB;
import fr.test.java.modele.Utilisateur;

/**
 * Servlet implementation class SvlCreationUser
 */
@WebServlet("/newUser")
public class SvlCreationUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String myUsername = (String) session.getAttribute("username");

		this.getServletContext().getRequestDispatcher("/viewJSP/creationNewUser.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String tmpUsername, tmpPassword, tmpName, tmpSurname, tmpEmail, tmpTelephone;
		UtilisateurDB getUser = new UtilisateurDB();
		boolean userExist = false;
		UtilisateurDB userDB = new UtilisateurDB();

		tmpUsername = request.getParameter("username");
		tmpPassword = request.getParameter("password");
		tmpName = request.getParameter("name");
		tmpSurname = request.getParameter("surname");
		tmpEmail = request.getParameter("email");
		tmpTelephone = request.getParameter("telephone");
		if (tmpUsername.length() > 0) {
			try {
				userExist = getUser.isExistUser(tmpUsername);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (!userExist) {
				Utilisateur tmpUser = new Utilisateur(tmpUsername, tmpEmail, tmpPassword, tmpName, tmpSurname,
						tmpTelephone);
				int tmpAnswer = userDB.ajouterUser(tmpUser);
				System.out.println("result insert = " + tmpAnswer);
				if (tmpAnswer == 1) {
					response.sendRedirect(request.getContextPath() + "/login?created=complete");
				}
			}
			response.sendRedirect(request.getContextPath() + "/newUser?error=1");
		}
	}

}
