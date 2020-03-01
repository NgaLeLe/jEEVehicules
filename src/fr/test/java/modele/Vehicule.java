package fr.test.java.modele;

public class Vehicule {
	protected String marque;
	protected String modele;
	protected int nombreRoues;
	private String immatriculation;
	private String typeVehicule;

	public String getTypeVehicule() {
		return typeVehicule;
	}

	public boolean isVoiture() {
		return typeVehicule.toLowerCase().contains("voiture");
	}

	public boolean isMotocyclette() {
		return typeVehicule.toLowerCase().contains("motocyclette");
	}

	public void setTypeVehicule(String typeVehicule) {
		this.typeVehicule = typeVehicule;
	}

	// constructeur
	public Vehicule() {
	}

	public Vehicule(String marque, String modele, int nombreRoues) {
		this.marque = marque;
		this.modele = modele;
		this.nombreRoues = nombreRoues;
	}

	public Vehicule(String marque, String modele, int nombreRoues, String immatriculation) {
		this(marque, modele, nombreRoues);
		this.setImmatriculation(immatriculation);
	}

	public String getMarque() {
		return marque;
	}

	public void setMarque(String marque) {
		this.marque = marque;
	}

	public String getModele() {
		return modele;
	}

	public void setModele(String modele) {
		this.modele = modele;
	}

	public int getNombreRoues() {
		return nombreRoues;
	}

	public void setNombreRoues(int nombreRoues) {
		this.nombreRoues = nombreRoues;
	}

	public String getImmatriculation() {
		return immatriculation;
	}

	public void setImmatriculation(String immatriculation) {
		this.immatriculation = immatriculation;
	}

	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append("immat ").append(getImmatriculation()).append(", marque ").append(getMarque()).append(", modele ")
				.append(getModele()).append(", nb roues ").append(getNombreRoues());
		return string.toString();
	}

	public String toStringSeparator(char nomSeparator) {
		StringBuilder string = new StringBuilder();
		string.append(nomSeparator).append(getImmatriculation()).append(nomSeparator).append(getMarque())
				.append(nomSeparator).append(getModele()).append(nomSeparator).append(getNombreRoues());
		return string.toString();
	}

}
