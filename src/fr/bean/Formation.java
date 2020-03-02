package fr.bean;

public class Formation {
	private String nomFormation;
	private String period;
	private String lieu;

	public Formation() {
	}

	public Formation(String nomFormation, String period, String lieu) {
		super();
		this.nomFormation = nomFormation;
		this.period = period;
		this.lieu = lieu;
	}

	public String getNomFormation() {
		return nomFormation;
	}

	public void setNomFormation(String nomFormation) {
		this.nomFormation = nomFormation;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getLieu() {
		return lieu;
	}

	public void setLieu(String lieu) {
		this.lieu = lieu;
	}

	/*
	 * SELECT nom_for, periode_for, lieu_for FROM public.formation;
	 */
}
