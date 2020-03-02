package fr.controlleur;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SvlReservation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SvlReservation() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getSession().getAttribute("formateurName") != null) {
			this.getServletContext().getRequestDispatcher("/view/semainier.html").forward(request, response);
		} else {
			response.sendRedirect("login");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String mySalle = request.getParameter("salleName");
		int id_mySalle = findIdSalle(mySalle);
//		ReservationDAO reservation = new ReservationDAO();
//		List<Reservation> listBySalle = reservation.showBySalle(id_mySalle);
		HttpSession session = request.getSession();
		session.setAttribute("salleName", mySalle);
		session.setAttribute("id_salle", id_mySalle);
		String[] cell_selecte = request.getParameterValues("selecte");
		System.out.println("cell " + cell_selecte);
		response.sendRedirect(request.getContextPath() + "/reservation?reserve=1");
	}

	private int findIdSalle(String salleName) {
		int tmpId_Salle = 0;
		switch (salleName) {
		case "openspace":
			tmpId_Salle = 1;
			break;
		case "theo":
			tmpId_Salle = 2;
			break;
		case "plato":
			tmpId_Salle = 3;
			break;
		case "buro":
			tmpId_Salle = 4;
			break;
		default:
			break;
		}
		return tmpId_Salle;
	}
}
