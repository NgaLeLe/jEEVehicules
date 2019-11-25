package Gestion;

import java.util.*;

import Apprenant.*;

public class Execute {
//afficher menu d'application
	public String menu(Scanner sc) {
		String ansChoix = null;
		System.out.println("---- MENU ----");
		System.out.println("1. Apprenant");
		System.out.println("2. Promo");
		System.out.println("3. Finit !!!");
		System.out.println("Voulez vous choisir ?");
		ansChoix = sc.nextLine();

		return ansChoix;
	}

//tranformer la choix qu'utilisateur a prise
	private char nbChoix(String pChoice) {
		char tmpC = pChoice.trim().charAt(0);
		if (tmpC == '1' || tmpC == 'A') {
			tmpC = '1';
		} else if (tmpC == '2' || tmpC == 'P') {
			tmpC = '2';
		} else if (tmpC == '3' || tmpC == 'R') {
			tmpC = '3';
		} else if (tmpC == '4' || tmpC == 'A') {
			tmpC = '4';
		} else {
			tmpC = '5';
		}
		return tmpC;
	}

//lancer mon application
	public void run() {
		Scanner input = new Scanner(System.in);
		String ans;
		Promo.creerListPromo(); // je creé liste promo défault

		do {
			String myMenu = menu(input);

			switch (nbChoix(myMenu)) {
			case '1':
				gestionApprenant(input);
				break;
			case '2':
				gestionPromo(input);
				break;
			case '3':
				break;
			default:
				break;
			}
			System.out.println("Continue? Oui(Yes)/Non(No): ");
			ans = input.nextLine();

		} while (ans.toLowerCase().contains("oui") || ans.toLowerCase().contains("yes"));
	}

	// menu de gestion des promo
	public void gestionPromo(Scanner input) {
		String tchoix, pNom, pPrenom;
		Integer tIdPromo, tNbJour, pDay, pMonth, pMinute;

		System.out.println("Gestion Promo");
		System.out.println("1. Ajouter list apprenant");
		System.out.println("2. Changer nombre jour effectue d'un promo");
		System.out.println("3. Absence d'un apprenant");
		System.out.println("4. Retard d'un apprenant ");
		System.out.println("5. Afficher promo ");
		System.out.println("Choisir ???");
		tchoix = input.nextLine();
		tchoix = tchoix.trim().toLowerCase();
		if (tchoix.contains("ajo") || tchoix.contains("1")) {
			System.out.println("ID promo");
			tIdPromo = Integer.parseInt(input.nextLine());
			Promo.ajouterListAprrenant(tIdPromo, input);
			System.out.println("Ajouté");
			return;
		} else if (tchoix.contains("cha") || tchoix.contains("2")) {
			System.out.print("ID promo que vous voulez changer le nombre de jour effectue: ");
			tIdPromo = Integer.parseInt(input.nextLine());
			System.out.println("Nombre de jour effectue: ");
			tNbJour = Integer.parseInt(input.nextLine());
			if (tIdPromo <= Promo.listPromo.size()) {
				Promo.listPromo.get(tIdPromo).setDureeEffecture(tNbJour);
				System.out.println();
				return;
			} else {
				System.out.println("Ce promo n'existe pas!");
				return;
			}
		} else if (tchoix.contains("aff") || tchoix.contains("5")) { // j'affiche liste Promo
			Promo.AfficherListPromo();
			return;
		} else if (tchoix.contains("abs") || tchoix.contains("3")) { // j'ajoute un jour d'absence d'n apprenant
			System.out.println("Saisie apprenant et jour d'absence: ");
			System.out.print("Nom = ");
			pNom = input.nextLine();
			System.out.print("Prenom = ");
			pPrenom = input.nextLine();
			System.out.print("jour = ");
			pDay = Integer.parseInt(input.nextLine());
			System.out.print("mois = ");
			pMonth = Integer.parseInt(input.nextLine());
			Promo.ajouterJourAbsence(pNom, pPrenom, pDay, pMonth);
			return;
		} else if (tchoix.contains("re") || tchoix.contains("4")) { // j'ajoute nombre de minutes tardes
			System.out.println("Saisie apprenant et nombre de minutes retards: ");
			System.out.print("Nom = ");
			pNom = input.nextLine();
			System.out.print("Prenom = ");
			pPrenom = input.nextLine();
			System.out.print("combien de minute tarde = ");
			pMinute = Integer.parseInt(input.nextLine());
			Promo.ajouterMinuteRetard(pNom, pPrenom, pMinute);
			return;
		} else {
			return;
		}
	}

//menu de travail de gestion d'apprenant
	public void gestionApprenant(Scanner input) {
		String tchoix, pType, pNom, pPrenom, pEmail, pTelephone;
		System.out.println("Apprenant---");
		System.out.println("Ajouter ");
		System.out.println("Supprimer ");
		System.out.println("Afficher");
		System.out.println("Choisir ???");
		tchoix = input.nextLine();
		tchoix = tchoix.trim().toLowerCase();
		if (tchoix.contains("aj")) { // ajouter un apprenant
			System.out.print("Nom = ");
			pNom = input.nextLine();
			System.out.print("Prenom = ");
			pPrenom = input.nextLine();
			System.out.println("Jour (1->31) = ");
			Integer date = Integer.parseInt(input.nextLine());
			System.out.println("Mois (1->12) = ");
			Integer month = Integer.parseInt(input.nextLine());
			System.out.println("Email (xyz@ttt.yyy: ");
			pEmail = input.nextLine();
			System.out.println("Telephone: ");
			pTelephone = input.nextLine();
			System.out.print("Stagiare/Alternant");

			do {
				pType = input.nextLine();
				pType = pType.toLowerCase().trim();
				System.out.println("resaisir type: ");
				pType = input.nextLine();
				pType = pType.toLowerCase().trim();
			} while (pType.contains("alt") == false || pType.contains("sta") == false);
			if (pType.contains("sta")) { // si apprenant est stagiaire
				System.out.print("quel type de stagiare");
				String motif = input.nextLine();
				System.out.print("Montant d'allocation: ");
				Double mAllo = Double.parseDouble(input.nextLine());
				Apprenant.ajouterStagiaire(pNom, pPrenom, new Date(2019, month, date), pEmail, pTelephone, motif,
						mAllo);
			} else if (pType.contains("alt")) { // si apprenant est alternant
				System.out.print("Quel entreprise : ");
				String company = input.nextLine();
				System.out.print("Salaire : ");
				Double salaire = Double.parseDouble(input.nextLine());
				Apprenant.ajouterAlternant(pNom, pPrenom, new Date(2019, month, date), pEmail, pTelephone, company,
						salaire);
			}

		} else if (tchoix.contains("su")) {
			System.out.print("Nom = ");
			pNom = input.nextLine();
			System.out.print("Prenom = ");
			pPrenom = input.nextLine();
			Apprenant.suprimerApprenant(pNom, pPrenom);
			return;
		} else if (tchoix.contains("aff")) {
			Apprenant.afficherApprenant();
			return;
		} else if (tchoix.contains("sta")) {
			//
		} else {
			return;
		}
	}

}
