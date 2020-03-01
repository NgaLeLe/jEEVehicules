package fr.test.java.modele;

public class Utilisateur {
	private int id_user;
	private String userName;
	private String motPass;
	private String name;
	private String surName;
	private String telephone;
	private String role;
	private String email;

	public Utilisateur(int id_user, String role, String userName, String surName, String motPass, String email) {
		this.id_user = id_user;
		this.userName = userName;
		this.email = email;
		this.motPass = motPass;
		this.surName = surName;
		this.role = role;
	}

//constructeur défault
	public Utilisateur() {

	}

//constructeur parametre
	public Utilisateur(String userName, String email, String motPass, String name, String surName, String telephone) {
		this.userName = userName;
		this.email = email;
		this.motPass = motPass;
		this.name = name;
		this.surName = surName;
		this.telephone = telephone;
	}

	public Utilisateur(String userName, String motPass) {
		this.userName = userName;
		this.motPass = motPass;
	}

	public int getIdUser() {
		return id_user;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public String getMotPass() {
		return motPass;
	}

	public void setMotPass(String motPass) {
		this.motPass = motPass;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

}
