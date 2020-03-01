package fr.test.java.modele;

public class Motocyclette extends Vehicule {

	private int puissance;

	public Motocyclette() {
		super();
		super.nombreRoues = 2;
	}

	public Motocyclette(String typeVehicule, String marque, String modele, int pPuissance) {
		super(marque, modele, 2);
		super.setTypeVehicule(typeVehicule);
		this.puissance = pPuissance;
	}

	public Motocyclette(String typeVehicule, String marque, String modele, int pPuissance, String immatriculation) {
		this(typeVehicule, marque, modele, pPuissance);
		this.setImmatriculation(immatriculation);
	}

	public int getPuissance() {
		return puissance;
	}

	public void setPuissance(int puissance) {
		this.puissance = puissance;
	}

	@Override
	public String toString() {
		StringBuffer str = new StringBuffer();
		str.append("Motocyclette ").append(super.toString()).append(", puissance ").append(getPuissance());
		return str.toString();
	}

	@Override
	public String toStringSeparator(char nomSeparator) {
		StringBuffer str = new StringBuffer();
		str.append("Motocyclette").append(super.toStringSeparator(nomSeparator)).append(nomSeparator)
				.append(getPuissance()).append("\n");
		return str.toString();
	}

	public static Motocyclette ajouter(String myTypeVehicule, String myImma, String myMarque, String myModele,
			Integer myPuissance) {
		{
			return new Motocyclette(myTypeVehicule, myMarque, myModele, myPuissance, myImma);
		}
	}
}
