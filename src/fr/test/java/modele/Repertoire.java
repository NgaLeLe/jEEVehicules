package fr.test.java.modele;

public class Repertoire {

	private int id_repertoire;
	private String nom_repertoire;

	public Repertoire() {

	}

	public Repertoire(int id_repertoire, String nom_repertoire) {
		this.id_repertoire = id_repertoire;
		this.nom_repertoire = nom_repertoire;
	}

	public int getId_repertoire() {
		return id_repertoire;
	}

	public void setId_repertoire(int id_repertoire) {
		this.id_repertoire = id_repertoire;
	}

	public String getNom_repertoire() {
		return nom_repertoire;
	}

	public void setNom_repertoire(String nom_repertoire) {
		this.nom_repertoire = nom_repertoire;
	}

}
