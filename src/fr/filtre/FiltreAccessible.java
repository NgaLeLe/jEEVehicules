package fr.filtre;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.service.CheckUser;
import fr.service.UtilisateurDB;
import fr.test.java.modele.Utilisateur;

/**
 * Servlet Filter implementation class FiltreAccessible
 */
@WebFilter("/FiltreAccessible")
public class FiltreAccessible implements Filter {

	/**
	 * Default constructor.
	 */
	public FiltreAccessible() {
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession(); // récupère session
		UtilisateurDB tmpUtilisateur = new UtilisateurDB();

		String fUser = (String) session.getAttribute("username");
		String fPass = (String) session.getAttribute("password");
		Utilisateur user = null;
		if (fUser != null && fPass != null) { // l'utilisateur n'a pas saisi pas username ni password
			try {
				user = tmpUtilisateur.chercherUser(fUser, fPass); // requete ça cherche ce username qui exist sous BD
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
			// si utilisateur a saisi username/pass qui ne sont pas exist dans BD
			System.out.println("URI = " + req.getRequestURI());
			if (user == null && fUser != null && req.getRequestURI().endsWith("toto")) {
				res.sendRedirect("login");
//				return;
//			} else {
//				if (fUser != null && !CheckUser.checkAd(user.getUserName()) && req.getRequestURI().endsWith("toto")) {
//					res.sendRedirect("login");
//					return;
//				}
			}
		} else {
			chain.doFilter(req, res);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
