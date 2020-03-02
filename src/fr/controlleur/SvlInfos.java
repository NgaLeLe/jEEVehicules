package fr.controlleur;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.bean.Competence;
import fr.bean.Coordonne;
import fr.bean.Experience;
import fr.bean.Formation;
import fr.service.GetInfo;

/**
 * Servlet implementation class SvlInfos
 */
public class SvlInfos extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SvlInfos() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// déclarer var avec contenue de Coordonnee, competences
		Coordonne myCoordonnee = new Coordonne();
		List<Competence> myCompetences = new ArrayList<Competence>();
		List<Experience> myExperiences = new ArrayList<Experience>();
		List<Formation> myFormations = new ArrayList<Formation>();
		// prend les infors de DB comCV
		try {
			myCompetences = GetInfo.getCompetence(); // recupere les competences
			myCoordonnee = GetInfo.getCoordonne(); // recupere les coordonnees
			myExperiences = GetInfo.getExperience(); // recupre les experiences
			myFormations = GetInfo.getFormation(); // recupere les formations
			System.out.println("recevoir my coordonnee !!!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// envoyer les infors à request
		request.setAttribute("nom", myCoordonnee.getNom());
		request.setAttribute("prenom", myCoordonnee.getPrenom());
		request.setAttribute("adress", myCoordonnee.getAdress());
		request.setAttribute("codePostal", myCoordonnee.getCodepostal());
		request.setAttribute("ville", myCoordonnee.getVille());
		request.setAttribute("email", myCoordonnee.getEmail());
		request.setAttribute("presentation", myCoordonnee.getPresentation());
		request.setAttribute("demande", myCoordonnee.getDemande());
		System.out.println(myCoordonnee.getDemande());
		// envoyer les compétences à jsp
		request.setAttribute("competences", myCompetences);
		request.setAttribute("experiences", myExperiences);
		request.setAttribute("formations", myFormations);
		request.getRequestDispatcher("/about").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
