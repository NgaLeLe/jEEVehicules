package fr.bean;

public class Experience {
	private String nomExperience;
	private String periode;
	private String lieu;
	private String detail;

	public Experience() {
		super();
	}

	public Experience(String nomExperience, String periode, String lieu, String detail) {
		super();
		this.nomExperience = nomExperience;
		this.periode = periode;
		this.lieu = lieu;
		this.detail = detail;
	}

	public String getNomExperience() {
		return nomExperience;
	}

	public void setNomExperience(String nomExperience) {
		this.nomExperience = nomExperience;
	}

	public String getPeriode() {
		return periode;
	}

	public void setPeriode(String periode) {
		this.periode = periode;
	}

	public String getLieu() {
		return lieu;
	}

	public void setLieu(String lieu) {
		this.lieu = lieu;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	/*
	 * SELECT nom_exp, period, lieu, detail FROM public.experience;
	 */
}
