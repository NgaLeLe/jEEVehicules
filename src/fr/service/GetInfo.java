package fr.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.bean.*;
import fr.test.db.ConnectionDB;

public class GetInfo {
	static private String selectCoordonnee = "SELECT nom, prenom, adress, codepostal, ville, telephone, email, presentation, demande FROM public.coordonnee;";
	static private String selectCompetence = "SELECT type, nom_com FROM public.competence;";
	static private String selectExperience = "SELECT nom_exp, period, lieu, detail FROM public.experience;";
	static private String selectFormation = "SELECT nom_for, periode_for, lieu_for FROM public.formation;";

	public GetInfo() {
		super();
	}

	// méthode à retire la contenue de tableau Coordonnee dans DB monCV
	public static Coordonne getCoordonne() throws SQLException {
		Coordonne coordonne = new Coordonne();
		Connection con = ConnectionDB.connect(); // creer une connect à DB monCV
		Statement statement = con.createStatement(); // creer une statement de requête
		ResultSet result = statement.executeQuery(selectCoordonnee); // exec Query et mettre result à var typé ResultSet
		while (result.next()) { // chaque élement de resultset est mis à l'attribut de classe Coordonnee associé
			coordonne.setNom(result.getString(1));
			coordonne.setPrenom(result.getString(2));
			coordonne.setAdress(result.getString(3));

			coordonne.setCodepostal(result.getString(4));
			coordonne.setVille(result.getString(5));
			coordonne.setEmail(result.getString(6));
			coordonne.setPresentation(result.getString(7));
			coordonne.setDemande(result.getString(8));
		}
		statement.close(); // detruire statement
		result.close(); // detruire resultset
		return coordonne;
	}

	public static List<Competence> getCompetence() throws SQLException {
		List<Competence> myCompetences = new ArrayList<Competence>();
		Connection con = ConnectionDB.connect(); // creer une connect à DB monCV
		Statement statement = con.createStatement(); // creer une statement de requête
		ResultSet result = statement.executeQuery(selectCompetence); // exec Query et mettre result à var typé ResultSet
		while (result.next()) { // chaque élement de resultset est mis à l'attribut de classe Coordonnee associé
			myCompetences.add(new Competence(result.getString(1), result.getString(2)));
		}
		statement.close(); // detruire statement
		result.close(); // detruire resultset
		return myCompetences;
	}

	public static List<Experience> getExperience() throws SQLException {
		List<Experience> myExperience = new ArrayList<Experience>();
		int i = 0;
		Connection con = ConnectionDB.connect(); // creer une connect à DB monCV
		Statement statement = con.createStatement(); // creer une statement de requête
		ResultSet result = statement.executeQuery(selectExperience); // exec Query et mettre result à var typé ResultSet
		while (result.next()) { // chaque élement de resultset est mis à l'attribut de classe Coordonnee associé
			myExperience.add(
					new Experience(result.getString(1), result.getString(2), result.getString(3), result.getString(4)));
			i++;
		}
		statement.close(); // detruire statement
		result.close(); // detruire resultset
		return myExperience;
	}

	public static List<Formation> getFormation() throws SQLException {
		List<Formation> myFormation = new ArrayList<Formation>();
		int i = 0;
		Connection con = ConnectionDB.connect(); // creer une connect à DB monCV
		Statement statement = con.createStatement(); // creer une statement de requête
		ResultSet result = statement.executeQuery(selectFormation); // exec Query et mettre result à var typé ResultSet
		while (result.next()) { // chaque élement de resultset est mis à l'attribut de classe Coordonnee associé
			myFormation.add(new Formation(result.getString(1), result.getString(2), result.getString(3)));
			i++;
		}
		statement.close(); // detruire statement
		result.close(); // detruire resultset
		return myFormation;
	}

	public static void main(String[] args) throws SQLException {
//		GetInfo info = new GetInfo();
		List<Experience> myFormation = getExperience();
		System.out.println("Done");
		for (Experience formation : myFormation) {
			System.out.println(formation.getDetail().toString());
		}
	}
}
