package fr.test.java.modele;

public class Fichier {
	private int id_fichier;
	private String nom_fichier;
	private int id_parent;
	private boolean dossier = false;
	private String chemin_acces;

	public Fichier() {
	}

	public Fichier(int id_fichier, String nom_fichier, int id_parent, boolean dossier, String chemin_acces) {
		this.id_fichier = id_fichier;
		this.nom_fichier = nom_fichier;
		this.id_parent = id_parent;
		this.dossier = dossier;
		this.chemin_acces = chemin_acces;
	}

	public int getId_fichier() {
		return id_fichier;
	}

	public void setId_fichier(int id_fichier) {
		this.id_fichier = id_fichier;
	}

	public String getNom_fichier() {
		return nom_fichier;
	}

	public void setNom_fichier(String nom_fichier) {
		this.nom_fichier = nom_fichier;
	}

	public int getId_parent() {
		return id_parent;
	}

	public void setId_parent(int id_parent) {
		this.id_parent = id_parent;
	}

	public boolean isDossier() {
		return dossier;
	}

	public void setDossier() {
		this.dossier = true;
	}

	public String getChemin_acces() {
		return chemin_acces;
	}

	public void setChemin_acces(String chemin_acces) {
		this.chemin_acces = chemin_acces;
	}

}
