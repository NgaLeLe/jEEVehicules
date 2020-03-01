package fr.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.test.db.ConnectionDB;
import fr.test.java.modele.Motocyclette;
import fr.test.java.modele.Vehicule;
import fr.test.java.modele.Voiture;

public class VehiculeDB {
	static public List<Vehicule> list;
	private Connection con = null;
	private PreparedStatement statement = null;
	private ResultSet result = null;

	static private String LIST_VEHICULE_DB = "SELECT immatriculation, marque, modele, "
			+ "couleur, annee, puissance, type_vehicule FROM public.vehicule;";
	static private String AJOUTER_VEHICULE = "INSERT INTO vehicule( immatriculation, marque, modele, annee, couleur, puissance, type_vehicule)"
			+ " VALUES (?, ?, ?, ?, ?, ?, ?);";
	static private String DELETE_VEHICULE = "DELETE FROM vehicule WHERE immatriculation = ? ;";

	public int supprimerVehicule(String pImmatriculation) {
		int answer = 0;
		con = ConnectionDB.connect();
		try {
			statement = con.prepareStatement(DELETE_VEHICULE);
			statement.setString(1, pImmatriculation);
			answer = statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return answer;
	}

	public int ajouterVehicule(Vehicule pVehiculeAjout) {
		int answer = 0;
		try {
			con = ConnectionDB.connect();
			statement = con.prepareStatement(AJOUTER_VEHICULE);
			statement.setString(1, pVehiculeAjout.getImmatriculation());
			statement.setString(2, pVehiculeAjout.getMarque());
			statement.setString(3, pVehiculeAjout.getModele());
			statement.setString(7, pVehiculeAjout.getTypeVehicule());
			if (pVehiculeAjout.isVoiture()) {
				Voiture v = (Voiture) pVehiculeAjout;
				statement.setInt(4, v.getAnnee());
				statement.setString(5, v.getCouleur());
				statement.setInt(6, 0);
			}
			if (pVehiculeAjout.isMotocyclette()) {
				Motocyclette m = (Motocyclette) pVehiculeAjout;
				statement.setInt(4, 0);
				statement.setString(5, null);
				statement.setInt(6, m.getPuissance());
			}
			answer = statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return answer;
	}

	public List<Vehicule> getListVehicule() {
		list = new ArrayList<Vehicule>();
		Vehicule tmpVehicule = null;
		String tmpImma = null, type = null;

		try { // prépare query Select avec var LIST_VEHICULE_DB
			con = ConnectionDB.connect();
			statement = con.prepareStatement(LIST_VEHICULE_DB);
			result = statement.executeQuery();// execute le query et le résultat se met dans var result
			while (result.next()) { // parcourir resultset
				type = result.getString(7); // récupère le contenue de type_vehicule
				tmpImma = result.getString(1); // reécupère l'immatriculation
				if (isMoto(type)) { // si est un moto, créer un moto
					tmpVehicule = new Motocyclette(type, result.getString("marque"), result.getString("modele"),
							result.getInt("puissance"), result.getString(1));

				}
				if (isVoiture(type)) { // si est un moto, créer un moto
					tmpVehicule = new Voiture(type, result.getString("marque"), result.getString("modele"),
							result.getInt("annee"), result.getString("couleur"), result.getString(1));

				}
				list.add(tmpVehicule);// ajoute un véhicule dans list
			}
			result.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	private static boolean isMoto(String pType) {
		if ("Motocyclette".equalsIgnoreCase(pType)) {
			return true;
		} else {
			return false;
		}
	}

	private static boolean isVoiture(String pType) {
		if ("Voiture".equalsIgnoreCase(pType)) {
			return true;
		} else {
			return false;
		}
	}

}
