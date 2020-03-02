package fr.test.java.modele;

import java.util.Date;

public class Reservation {
	private int id_formateur;
	private int id_salle;
	private int id_reservation;
	private Date jour;
	private int heure_debut;
	private int heure_fin;

	public Reservation() {
	}

	public Reservation(int id_reservation, int id_formateur, int id_salle, Date jour, int heure_debut, int heure_fin) {
		this.id_formateur = id_formateur;
		this.id_salle = id_salle;
		this.id_reservation = id_reservation;
		this.jour = jour;
		this.heure_debut = heure_debut;
		this.heure_fin = heure_fin;
	}

	public Reservation(int id_formateur, int id_salle, Date jour, int heure_debut, int heure_fin) {
		super();
		this.id_formateur = id_formateur;
		this.id_salle = id_salle;
		this.jour = jour;
		this.heure_debut = heure_debut;
		this.heure_fin = heure_fin;
	}

	public int getId_reservation() {
		return id_reservation;
	}

	public int getId_formateur() {
		return id_formateur;
	}

	public void setId_formateur(int id_formateur) {
		this.id_formateur = id_formateur;
	}

	public int getId_salle() {
		return id_salle;
	}

	public void setId_salle(int id_salle) {
		this.id_salle = id_salle;
	}

	public Date getJour() {
		return jour;
	}

	public void setJour(Date jour) {
		this.jour = jour;
	}

	public int getHeure_debut() {
		return heure_debut;
	}

	public void setHeure_debut(int heure_debut) {
		this.heure_debut = heure_debut;
	}

	public int getHeure_fin() {
		return heure_fin;
	}

	public void setHeure_fin(int heure_fin) {
		this.heure_fin = heure_fin;
	}

}
