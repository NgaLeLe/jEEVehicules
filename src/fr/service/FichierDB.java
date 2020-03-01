package fr.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import fr.test.db.ConnectionDB;
import fr.test.java.modele.*;

public class FichierDB {
//	private static String SELECT_FICHIER = "";
	private static String INSERT_FICHIER = "INSERT INTO fichiers(nom_fichier, id_parent, dossier, chemin_acces) VALUES ( ?, ?, ?, ?);";

	private Connection connect = ConnectionDB.connect();
	private PreparedStatement statement = null;

	private List<Fichier> tmpListFichier;

	public void ajouterListFichier() {
		ListFile listFile = new ListFile();
		tmpListFichier = listFile.creerListFichier();
		try {
			int count = 0;
			statement = connect.prepareStatement(INSERT_FICHIER);
			for (Fichier fichier : tmpListFichier) {

				statement.setString(1, fichier.getNom_fichier());
				statement.setInt(2, fichier.getId_parent());
				statement.setBoolean(3, fichier.isDossier());
				statement.setString(4, fichier.getChemin_acces());
				statement.addBatch();
				count++;
				if (count % 100 == 0 || count == tmpListFichier.size()) {
					statement.executeBatch();
				}
			}
			statement.close();
		} catch (

		SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		System.out.println("AJOUTER");
		FichierDB a = new FichierDB();
		a.ajouterListFichier();

		System.out.println("OK");
	}
}
