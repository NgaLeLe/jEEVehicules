package fr.test.db;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.Date;

public class ConnectionDB {
	// définit chemin url de DB, username et pass
//	private static final String databaseURL = "jdbc:postgresql://localhost:5432/Vehicule";
//	private static final String user = "postgres";
//	private static final String password = "nga";
	private static Connection con = null;

	public ConnectionDB() {

	}

	public static Connection connect() {
		LoaderProperties prop = new LoaderProperties();
		try {
			prop.loader();
		} catch (FileNotFoundException e2) {
			e2.printStackTrace();
		}

		try {
			Class.forName(prop.getDriver()); // déclare driver pour DB Postgres
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		try { // créer une connection to DB, avec 3 parametres
				// structure URL connection pour DB
			con = DriverManager.getConnection(prop.getDatabaseURL(), prop.getUserDB(), prop.getPassDB());
			// etablie une connection vers PostgresSQL
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;

	}

	public void closeConnection() throws SQLException {
		con.close();
	}

}
