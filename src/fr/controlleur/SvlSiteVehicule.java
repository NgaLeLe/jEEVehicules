package fr.controlleur;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.source.tree.RequiresTree;

import fr.service.VehiculeDB;
import fr.test.java.modele.*;

/**
 * Servlet implementation class SvlSiteVehicule
 */
@WebServlet("/SvlSiteVehicule")
public class SvlSiteVehicule extends HttpServlet {
	private static final long serialVersionUID = 1L;
	List<Vehicule> list = null;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// déclare les variables pour attraper toutes les valeurs des attributs dans le
		// formule
		list = (List<Vehicule>) request.getSession().getAttribute("vehicules");
		VehiculeDB vehiculeDb = new VehiculeDB();
		Voiture myVoiture;
		Motocyclette myMoto;

		if (list == null) {
			list = (List<Vehicule>) request.getSession().getAttribute("vehicules");
		}
		String type = request.getParameter("ftypeVeh");
		String myImma = request.getParameter("fImma");
		String myMarque = request.getParameter("fMarque");
		String myModele = request.getParameter("fModele");
		String myButton = request.getParameter("submit");
		if (myButton.contains("Ajouter")) {
			// si l'utilisateur a choisi button Voiture, type = "Voiture"
			if (type.toLowerCase().contains("voiture")) {
				String myCouleur = request.getParameter("fCouleur");
				Integer myAnnee = Integer.parseInt(request.getParameter("fAnnee"));
				myVoiture = new Voiture("Voiture", myMarque, myModele, myAnnee, myCouleur, myImma);
				list.add(myVoiture);
				vehiculeDb.ajouterVehicule(myVoiture);
			} else {
				Integer myPuissance = Integer.parseInt(request.getParameter("fPuissance"));
				myMoto = new Motocyclette("Motocyclette", myMarque, myModele, myPuissance, myImma);
				list.add(myMoto);
				vehiculeDb.ajouterVehicule(myMoto);
			}
		} else {
			if (myButton.contains("Supprimer")) {
				String myVehiculeSupprimer = request.getParameter("VehSupUpd");
				int index = findVehicule(myVehiculeSupprimer);
				if (index >= 0) {
					list.remove(index);
					vehiculeDb.supprimerVehicule(myVehiculeSupprimer.trim());
				}
			}
		}
		this.getServletContext().getRequestDispatcher("/viewJSP/tableVehicule.jsp").forward(request, response); // reappeler
	}

	// Servlet va envoyer les véhicules

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// requête de ce servlet peut envoyer à un autre servlet/page qui sont dans même
		// projet
		// la partie getServletContext().getRequestDispattcher() recupère
		this.getServletContext().getRequestDispatcher("/viewJSP/tableVehicule.jsp").forward(request, response);

	}

//chercher un Vehicule par immatriculation, if il exist, ça tourne son id dans la liste 
	private int findVehicule(String pImmatriculation) {
		int idVehicule = -1;
		for (int i = 0; i < list.size(); i++) {
			if (pImmatriculation.equalsIgnoreCase(list.get(i).getImmatriculation())) {
				idVehicule = i;
			}
		}
		return idVehicule;
	}
}
