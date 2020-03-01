package fr.controlleur;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.service.CheckUser;
import fr.service.UtilisateurDB;
import fr.service.VehiculeDB;
import fr.test.db.ConnectionDB;
import fr.test.java.modele.Utilisateur;
import fr.test.java.modele.Vehicule;

public class SvlLoginForm extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/viewJSP/login.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String myUsername, myPassword;
		List<Vehicule> list = null;
		VehiculeDB vehiculeDB = new VehiculeDB();
		List<Utilisateur> listUser = null;

		// récupère les contenues de parameter de request
		myUsername = req.getParameter("username");
		myPassword = req.getParameter("password");
		HttpSession session = req.getSession();

		try {
			// vérifié cet user qui est existe dans DB
			boolean tmpCheckUs = CheckUser.checkUs(myUsername, myPassword);
			boolean checkAd = CheckUser.checkAd(myUsername);
			session.setAttribute("username", myUsername); // gan thuoc tinh username vao session
			session.setAttribute("pass", myPassword);
			if (tmpCheckUs) {
				if (list == null) {
					list = vehiculeDB.getListVehicule(); // récupère Vehicule de DB
				}
				session.setAttribute("vehicules", list);
				// envoyer ce request à un autre servlet à traiter
				if (checkAd) { // est un admin, va le page admin.jsp
					if (listUser == null) {
						UtilisateurDB g = new UtilisateurDB();
						listUser = g.showAllUser(myUsername, myPassword);
					}
					session.setAttribute("listUser", listUser);
					resp.sendRedirect("admin");
				} else { // est un user normal, il peut acceder le page suivant tableVehicule.jsp
					resp.sendRedirect("toto");
				}
			} else { // si user n'exist pas, set nouveau parametre (utilise getAttribute) error de
						// request
				// ajouter une chaîne de character, à la fin de URL, avec nouveau parametre sous
				// forme query string de URL
				resp.sendRedirect(req.getContextPath() + "/login?error=1");
			}

		} catch (SQLException e) {
			e.printStackTrace();

		}
	}

}
