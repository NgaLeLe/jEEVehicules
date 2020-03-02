package fr.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import fr.test.java.modele.Reservation;
import fr.service.ReservationDAO;

public class VerificationSalleDispo {

	public boolean isDispo(int pIdSalle, Date pJour, int pHheureDebut, int pHeureFin) {
		ReservationDAO reser = new ReservationDAO();
		List<Reservation> list = reser.showAll();
		boolean tmpAns = true;
		for (Reservation l : list) {
			if ((l.getJour().equals(pJour)) && (l.getId_salle() == pIdSalle)) {
				if (!isBookable(l.getHeure_debut(), l.getHeure_fin(), pHheureDebut, pHeureFin)) {
					tmpAns = false;
					break;
				}
			}
		}
		return tmpAns;
	}

	private boolean isBookable(int heureDebutTest, int heureFinTest, int pHeureDebutReserve, int pHeureFinReserve) {
		return (((pHeureDebutReserve < heureDebutTest) && (pHeureFinReserve <= heureDebutTest))
				|| ((pHeureDebutReserve >= heureFinTest) && (pHeureFinReserve > heureFinTest)));
	}

	private Date convertDate(String pDate) {

		try {
			return new SimpleDateFormat("dd/MM/yyyy").parse(pDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		VerificationSalleDispo ver = new VerificationSalleDispo();

		Date day = ver.convertDate("20/02/2020");
		System.out.println(day);
		if (ver.isDispo(1, day, 12, 15)) {
			System.out.println("Dispo");
		}
	}
}