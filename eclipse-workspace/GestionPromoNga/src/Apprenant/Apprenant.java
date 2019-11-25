package Apprenant;

import java.util.*;

public class Apprenant {
	public static ArrayList<Apprenant> list = new ArrayList<Apprenant>(); // liste d'apprenants

	protected String nom;
	protected String prenom;
	protected Date dateInscription;
	protected String email;
	protected String telephone;
	protected ArrayList<Date> jourAbsent;
	protected int nbMinuteRetard = 0;

	// constructeur default
	public Apprenant() {
		this.nom = "";
		this.prenom = "";
		this.dateInscription = null;
		this.email = "";
		this.telephone = "";
		this.jourAbsent = null;

	}

	public Apprenant(String pNom, String pPrenom, Date pDateInsciption, String pEmail, String pTelephone) {
		this.nom = pNom;
		this.prenom = pPrenom;
		this.dateInscription = pDateInsciption;
		this.email = pEmail;
		this.telephone = pTelephone;
		this.jourAbsent = new ArrayList<Date>();
	}

	// getter & setter
	public String getNom() {
		return nom;
	}

	public void setNom(String pNom) {
		this.nom = pNom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String pPrenom) {
		this.prenom = pPrenom;
	}

	public Date getDateInscription() {
		return dateInscription;
	}

	public void setDateInsciption(Date pDateInsciption) {
		this.dateInscription = pDateInsciption;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String pEmail) {
		this.email = pEmail;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String pTelephone) {
		this.telephone = pTelephone;
	}

	public ArrayList<Date> getJourAbsent() {
		return jourAbsent;
	}

	public void setJourAbsent(int pDay, int pMonth) {
		jourAbsent.add(new Date(2019, pMonth, pDay));
	}

	public int getNbMinuteRetard() {
		return nbMinuteRetard;
	}

	public void setNnMinuteRetard(int nbMinuteRetard) {
		this.nbMinuteRetard = +nbMinuteRetard;
	}

	@Override
	public String toString() {
		return nom + "\t" + prenom + "\t" + dateInscription.getDate() + "/" + dateInscription.getMonth() + "/"
				+ dateInscription.getYear() + "\t" + email + "\t" + telephone + "\t" + jourAbsent + "\t"
				+ nbMinuteRetard + "\n";
	}

	// créer une liste d'apprenant pour tets
	public static ArrayList<Apprenant> creerListe(int start, int nbStagiaire, int nbAlternant) {
		ArrayList<Apprenant> ls = new ArrayList<Apprenant>();
		int i, j = 0;
		String[] name = { "Adil", "Nadine", "Dhafer", "Julien", "Nga", "Tham", "Shoulia", "Cedric", "Wilson", "Eddy" };
		String[] surname = { "XYZF", "Doukbe", "Shhka", "Le Mer", "Tran", "Tran", "Loukil", "Fechino", "Mendes",
				"Grosjean" };
		int[] day = { 15, 12, 1, 10, 10, 1, 6, 3, 15, 20 };
		int[] month = { 10, 11, 11, 10, 10, 10, 11, 11, 10, 11 };
		String[] email = { "adil@yahoo.fr", "nadine@gmail.com", "dhafer@yahoo.com", "julien@gmail.com", "abc@gmail.com",
				"xyxz@hotmail.fr", "bcett@yahoo.fr", "ssss@gmail.com", "zzurrr@gmail.com", "abcdef@gmail.com" };
		String[] tel = { "0964322784", "0909655433", "0798567845", "0907123456", "07007007007", "0698000000",
				"0612341234", "0789787787", "0646222222", "0666666666" };
		String[] typeAll = { "Chomage", "Pas travaillé", "Travaile pas assez", "CSI Nice", "Société Général", "Chomage",
				"Pas travaillé", "Travaile pas assez", "SAPSAP", "Adecco" };
		double[] sumAll = { 1510.56, 401.6, 650, 2750, 1967, 1510.56, 401.6, 650, 2450, 1600 };
		for (i = start; i < nbStagiaire + start; i++) {
			ls.add(new Stagiaire(surname[i], name[i], new Date(2019, month[i], day[i]), email[i], tel[i], typeAll[i],
					sumAll[i]));
		}
		for (i = nbStagiaire + start; i < nbStagiaire + start + nbAlternant; i++) {
			ls.add(new Alternant(surname[i], name[i], new Date(2019, month[i], day[i]), email[i], tel[i], typeAll[i],
					sumAll[i]));
		}
		return ls;
	}

	// ajouter un Stagiaire dans la liste
	public static void ajouterStagiaire(String pNom, String pPrenom, Date pDateInsciption, String pEmail,
			String pTelephone, String pType, double pMontantAll) {
		list.add(new Stagiaire(pNom, pPrenom, pDateInsciption, pEmail, pTelephone, pType, pMontantAll));
	}

	// ajouter un Alternant dans la liste
	public static void ajouterAlternant(String pNom, String pPrenom, Date pDateInsciption, String pEmail,
			String pTelephone, String pEntreprise, double pSalaire) {
		list.add(new Alternant(pNom, pPrenom, pDateInsciption, pEmail, pTelephone, pEntreprise, pSalaire));
	}

//imprimer liste d'apprenants
	public static void afficherApprenant() {
		System.out.println(
				"ID\tNom   \t Prenom   \t\t   Date     \tEmail   \tTelephone \t Type \tEntreprise\tSal_Allo\tJour absent\tNb minute retard");
		for (int i = 0; i < list.size(); i++) {
			System.out.println((i + 1) + "\t" + list.get(i));
		}
	}

	public static int isExist(String pNom, String pPrenom) {
		int position = -1;
		pNom = pNom.trim().toUpperCase();
		pPrenom = pPrenom.trim().toUpperCase();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getPrenom().trim().toUpperCase().contains((pPrenom))
					&& list.get(i).getNom().trim().toUpperCase().contains(pNom)) {
				position = i;
			}
		}
		return position;
	}

	public static void suprimerApprenant(String pNom, String pPrenom) {
		int tmpPos = isExist(pNom, pPrenom);
		if (tmpPos >= 0) {
			System.out.println("Vous venez de supprimer apprenant " + pNom + ", " + pPrenom);
			list.remove(tmpPos);
		} else {
			System.out.println("Cet apprenant n'existe pas.");
		}
	}

	public static ArrayList<Apprenant> getListApprenant() {
		return list;
	}
}
