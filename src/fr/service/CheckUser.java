package fr.service;

import java.sql.SQLException;
import fr.test.db.*;

public class CheckUser {
	public CheckUser() {
	}

//vérifier 
	public static boolean checkUs(String pUsername, String pPassword) throws SQLException {
		UtilisateurDB g = new UtilisateurDB();
		boolean ans = false;
		if (g.chercherUser(pUsername, pPassword) != null) {
			ans = true;
		}
		return ans;
	}

//vérifier que ce utilisateur est admin
	public static boolean checkAd(String pUsername) {
		if (pUsername.equalsIgnoreCase("admin")) {
			return true;
		} else {
			return false;
		}
	}
}
