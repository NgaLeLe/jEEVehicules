package fr.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import fr.test.java.modele.Reservation;
import fr.test.db.ConnectionDB;

public class ReservationDAO {
	static private String SELECT_ALL = "SELECT id_formateur, id_salle, jour, heure_debut, heure_fin "
			+ " FROM reservation  ;";

	static private String INSERT_RESERVATION = "INSERT INTO reservation(id_formateur, id_salle, jour, heure_debut, "
			+ "heure_fin) VALUES (?, ?, ?, ?, ?, ?);";

	static private String SELECT_SALLE = "SELECT id_formateur, id_salle, jour, heure_debut, heure_fin "
			+ " FROM reservation  WHERE id_salle = ? ";

	static private Connection con = ConnectionDB.connect();
	static private PreparedStatement statement;

	public List<Reservation> showAll() {
		List<Reservation> listReservation = new ArrayList<Reservation>();

		try {
			statement = con.prepareStatement(SELECT_ALL);

			ResultSet result = statement.executeQuery(); // exec Query et mettre result à
															// var typé
			// ResultSet
			while (result.next()) { // chaque élement de resultset est mis à l'attribut de classe Coordonnee associé
				listReservation.add(new Reservation(result.getInt(1), result.getInt(2), result.getDate(3),
						result.getInt(4), result.getInt(5)));
			}
			statement.close(); // detruire statement
			result.close(); // detruire resultset
		} catch (SQLException e) {
			e.printStackTrace();
		} // creer une statement de requête

		return listReservation;

	}

	public List<Reservation> showBySalle(int pIdSalle) {
		List<Reservation> listReservationSalle = new ArrayList<Reservation>();

		try {
			// creer une connect à DB monCV
			statement = con.prepareStatement(SELECT_SALLE);
			statement.setInt(1, pIdSalle); // gan id_salle de select

			ResultSet result = statement.executeQuery(); // exec Query et mettre result à
															// var typé
			// ResultSet
			while (result.next()) { // chaque élement de resultset est mis à l'attribut de classe Coordonnee associé
				listReservationSalle.add(new Reservation(result.getInt(1), result.getInt(2), result.getDate(3),
						result.getInt(4), result.getInt(5)));
			}
			statement.close(); // detruire statement
			result.close(); // detruire resultset
		} catch (SQLException e) {
			e.printStackTrace();
		} // creer une statement de requête

		return listReservationSalle;

	}

//permet d'inserer liste de réservation sous BD
	public void insertReservation(List<Reservation> reservationClient) {
		try {
			int count = 0;
			PreparedStatement statement = con.prepareStatement(INSERT_RESERVATION);
			for (Reservation reservation : reservationClient) {
				statement.setInt(1, reservation.getId_formateur()); // déclare les paramètres pour statement INSERT
				statement.setInt(2, reservation.getId_salle()); //
				statement.setDate(3, (Date) reservation.getJour());
				statement.setInt(4, reservation.getHeure_debut());
				statement.setInt(5, reservation.getHeure_fin());
				statement.addBatch();
				count++;
				if (count % 100 == 0 || count == reservationClient.size()) {
					statement.executeBatch(); // execute statement INSERT
				}
			}
			statement.close();
		} catch (

		SQLException e) {
			e.printStackTrace();
		}
	}

//	public static void main(String[] args) {
//		ReservationDAO reser = new ReservationDAO();
//		List<Reservation> l = reser.showBySalle(1);
//		int i = 0;
//		for (Reservation reservation : l) {
//			System.out.println(l.get(i).getId_salle() + "  " + l.get(i).getJour() + " " + l.get(i).getHeure_debut()
//					+ " " + l.get(i).getHeure_fin());
//			i++;
//		}
//	}
}
