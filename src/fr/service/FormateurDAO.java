package fr.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.test.db.ConnectionDB;
import fr.test.java.modele.Formateur;

public class FormateurDAO {
	public FormateurDAO() {
		// TODO Auto-generated constructor stub
	}

	public static Formateur getUser(String pUsername, String pPassword) throws SQLException {
		Formateur user = null;
		Connection connection = ConnectionDB.connect();
		PreparedStatement pStatement = connection.prepareStatement(
				"SELECT id_formateur, nom_access, password FROM public.\"formateur\" WHERE nom_access = ? AND password = ?;");
		pStatement.setString(1, pUsername);
		pStatement.setString(2, pPassword);
		ResultSet rs = pStatement.executeQuery();
		if (rs.next()) {
			user = new Formateur(rs.getShort(1), rs.getString("nom_access"), rs.getString("password"));
		}
		pStatement.close();
		rs.close();
		connection.close();
		return user;

	}

	public static boolean check(String pUsername, String pPassword) throws SQLException {
		boolean ans = false;
		if (getUser(pUsername, pPassword) != null) {
			ans = true;
		}
		return ans;
	}
}
