package fr.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.test.db.ConnectionDB;
import fr.test.java.modele.Motocyclette;
import fr.test.java.modele.Utilisateur;
import fr.test.java.modele.Vehicule;
import fr.test.java.modele.Voiture;

public class UtilisateurDB {
	static private String selectByUserPass = "SELECT username, password FROM public.\"Users\" WHERE username = ? AND password = ?;";
	static private String selectByUsername = "SELECT username, password FROM public.\"Users\" WHERE username = ? ";
	static private String selectAllUser = "SELECT id_user, role, username, surname, password, email  FROM public.\"Users\" WHERE username != ? AND password != ?;";
	static private String INSERT_USER = "INSERT INTO \"Users\"(username, password, name, surname, email, telephone) VALUES (?, ?, ?, ?, ?, ?);";

	private Connection con = null;
	private PreparedStatement statement = null;
	private ResultSet result = null;

	public UtilisateurDB() {
	}

	public int ajouterUser(Utilisateur pUserNew) {
		int answer = 0;
		try {
			con = ConnectionDB.connect();
			statement = con.prepareStatement(INSERT_USER);
			statement.setString(1, pUserNew.getUserName());
			statement.setString(2, pUserNew.getMotPass());
			statement.setString(3, pUserNew.getName());
			statement.setString(4, pUserNew.getSurName());
			statement.setString(5, pUserNew.getEmail());
			statement.setString(6, pUserNew.getTelephone());

			answer = statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return answer;
	}

	public Utilisateur chercherUser(String pUsername, String pPassword) throws SQLException {
		Utilisateur user = null;
		Connection connection = ConnectionDB.connect();
		PreparedStatement pStatement = connection.prepareStatement(selectByUserPass);
		pStatement.setString(1, pUsername);
		pStatement.setString(2, pPassword);
		ResultSet rs = pStatement.executeQuery();
		System.out.println("fonction chercher DB");
		while (rs.next()) {
			System.out.println("exist");
			user = new Utilisateur(rs.getString("username"), rs.getString("password"));
		}
		pStatement.close();
		rs.close();
		return user;

	}

	public List<Utilisateur> showAllUser(String pUsername, String pPassword) throws SQLException {
		List<Utilisateur> listUser = new ArrayList<Utilisateur>();
		Utilisateur user = null;
		Connection connection = ConnectionDB.connect();
		PreparedStatement pStatement = connection.prepareStatement(selectAllUser);
		pStatement.setString(1, pUsername);
		pStatement.setString(2, pPassword);
		ResultSet rs = pStatement.executeQuery();
		while (rs.next()) {
			user = new Utilisateur(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
					rs.getString(6));
			listUser.add(user);
		}
		pStatement.close();
		rs.close();
		return listUser;

	}

	public boolean isExistUser(String pUsername) throws SQLException {
		boolean tmpAnswer = false;
		Connection connection = ConnectionDB.connect();
		PreparedStatement pStatement = connection.prepareStatement(selectByUsername);
		pStatement.setString(1, pUsername);
		ResultSet rs = pStatement.executeQuery();
		if (rs.next()) {
			tmpAnswer = true;
		}
		pStatement.close();
		rs.close();
		return tmpAnswer;

	}

	public static void main(String[] args) throws SQLException {
		UtilisateurDB getUser = new UtilisateurDB();
		Utilisateur u = getUser.chercherUser("nga", "123456");
		System.out.println("name" + u.getName());
		System.out.println("tel" + u.getTelephone());
	}
}
