package Apprenant;

import java.util.Date;

//constructeur défault
public class Stagiaire extends Apprenant {
	private String typeAllocation; //
	private double montant;

	public Stagiaire() {
		super();
		this.typeAllocation = "";
		this.montant = 0;
	}

//constructeur avec parametrers
	public Stagiaire(String pNom, String pPrenom, Date pDateInsciption, String pEmail, String pTelephone,
			String pTypeAllocation, double pMontant) {
		super(pNom, pPrenom, pDateInsciption, pEmail, pTelephone);
		this.typeAllocation = pTypeAllocation;
		this.montant = pMontant;
	}

//getter & setter
	public String getTypeAllocation() {
		return typeAllocation;
	}

	public void setTypeAllocation(String typeAllocation) {
		this.typeAllocation = typeAllocation;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	@Override
	public String toString() {
		return super.toString() + "Stagiaire\t" + typeAllocation + "\t" + montant + "";
	}

}
