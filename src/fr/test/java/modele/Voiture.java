package fr.test.java.modele;

public class Voiture extends Vehicule {
	private String couleur;
	private int annee;

	// constructeur
	public Voiture() {
		super();
		super.setNombreRoues(4);
	}

	public Voiture(String typeVehicule, String marque, String modele, int pAnnee, String pCouleur) {
		super(marque, modele, 4);
		super.setTypeVehicule(typeVehicule);
		this.annee = pAnnee;
		this.couleur = pCouleur;
	}

	public Voiture(String typeVehicule, String marque, String modele, int pAnnee, String pCouleur,
			String immatriculation) {
		super(marque, modele, 4, immatriculation);
		super.setTypeVehicule(typeVehicule);
		this.annee = pAnnee;
		this.couleur = pCouleur;
	}

	public String getCouleur() {
		return couleur;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}

	public int getAnnee() {
		return annee;
	}

	public void setAnnee(int annee) {
		this.annee = annee;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("Voiture ").append(super.toString()).append(", couleur=").append(getCouleur()).append(", annee=")
				.append(getAnnee());
		return str.toString();
	}

	@Override
	public String toStringSeparator(char nomSeparator) {
		StringBuilder str = new StringBuilder();
		str.append("Voiture").append(super.toStringSeparator(nomSeparator)).append(";").append(getAnnee()).append(";")
				.append(getCouleur()).append("\n");
		return str.toString();
	}

}
