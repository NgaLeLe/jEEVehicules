package Apprenant;

import java.util.Date;

public class Alternant extends Apprenant {
	private String entreprise; // nom d'entreprise
	private double salaire; // somme de salaire

	// constructuer
	public Alternant() {
		super();
		this.entreprise = "";
		this.salaire = 0;
	}

	public Alternant(String pNom, String pPrenom, Date pDateInsciption, String pEmail, String pTelephone,
			String pEntreprise, double pSalaire) {
		super(pNom, pPrenom, pDateInsciption, pEmail, pTelephone);
		this.entreprise = pEntreprise;
		this.salaire = pSalaire;
	}

	public String getEntreprise() {
		return entreprise;
	}

	public void setEntreprise(String pEntreprise) {
		this.entreprise = pEntreprise;
	}

	public double getSalaire() {
		return salaire;
	}

	public void setSalaire(double pSalaire) {
		this.salaire = pSalaire;
	}

	@Override
	public String toString() {
		return super.toString() + "Alternant" + entreprise + "\t" + salaire + "";
	}

}
