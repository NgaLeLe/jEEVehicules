package fr.bean;

//classe qui contient les infos cordonnee de mon CV
public class Coordonne {
	private String nom;
	private String prenom;
	private String adress;
	private String codepostal;
	private String ville;
	private String email;
	private String presentation;
	private String demande;

	public Coordonne() {
		super();
	}

	public Coordonne(String nom, String prenom, String adress, String codepostal, String ville, String email,
			String presentation, String demande) {
		this.nom = nom;
		this.prenom = prenom;
		this.adress = adress;
		this.codepostal = codepostal;
		this.ville = ville;
		this.email = email;
		this.presentation = presentation;
		this.demande = demande;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public void setCodepostal(String codepostal) {
		this.codepostal = codepostal;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPresentation(String presentation) {
		this.presentation = presentation;
	}

	public void setDemande(String demande) {
		this.demande = demande;
	}

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public String getAdress() {
		return adress;
	}

	public String getCodepostal() {
		return codepostal;
	}

	public String getVille() {
		return ville;
	}

	public String getEmail() {
		return email;
	}

	public String getPresentation() {
		return presentation;
	}

	public String getDemande() {
		return demande;
	}

}
