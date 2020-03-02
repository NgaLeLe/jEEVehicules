package fr.test.java.modele;

public class Formateur {
	private int id_formateur;
	private String username;
	private String password;

	public Formateur() {
	}

	public Formateur(int id_formateur, String username, String password) {
		super();
		this.id_formateur = id_formateur;
		this.username = username;
		this.password = password;
	}

	public Formateur(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public int getId_formateur() {
		return id_formateur;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
