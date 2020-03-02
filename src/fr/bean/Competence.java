package fr.bean;

public class Competence {
	private String type;
	private String nomCompetence;

	public Competence() {
	}

	public Competence(String type, String nomCompetence) {
		this.type = type;
		this.nomCompetence = nomCompetence;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNomCompetence() {
		return nomCompetence;
	}

	public void setNomCompetence(String nomCompetence) {
		this.nomCompetence = nomCompetence;
	}

	/*
	 * SELECT type, nom_com FROM public.competence;
	 */
}
