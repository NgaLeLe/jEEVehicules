package fr.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.test.db.ConnectionDB;
import fr.test.java.modele.Repertoire;

public class RepertoireDB {
	static private String SELECT_REPERTOIRE = "SELECT id_repertoire, nom_repertoire FROM repertoire;";
	static private String AJOUTE_REPERTOIRE = "INSERT INTO repertoire(nom_repertoire) values (?);";

	private Connection connect = ConnectionDB.connect();
	private PreparedStatement statement = null;
	private ResultSet result = null;

	public List<Repertoire> getRepertoire() {
		List<Repertoire> list_repertoire = new ArrayList<Repertoire>();
		Repertoire tmpRepertoire = null;

		try {
			statement = connect.prepareStatement(SELECT_REPERTOIRE);
			result = statement.executeQuery();
			while (result.next()) {
				tmpRepertoire = new Repertoire(result.getInt(1), result.getString(2));
				list_repertoire.add(tmpRepertoire);
			}
			statement.close();
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list_repertoire;

	}

	public void insertNewRepertoire() {
		String nomRepertoire = "4_Sécurité_informatique";
		try {
			statement = connect.prepareStatement(AJOUTE_REPERTOIRE);
			statement.setString(1, nomRepertoire);
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
