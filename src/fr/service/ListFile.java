package fr.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import fr.test.java.modele.Fichier;
import fr.test.java.modele.Repertoire;

public class ListFile {
	private static String RACINE = "C:\\Users\\t.tran1\\Documents\\Nga";

	public List<Fichier> creerListFichier() {
		RepertoireDB reper = new RepertoireDB();
		List<Repertoire> listRepertoire = reper.getRepertoire();
		Repertoire ressource = listRepertoire.get(1);
		List<Fichier> listFichier = new ArrayList<Fichier>();

		StringBuilder tmpNomRepertoire = new StringBuilder();
		tmpNomRepertoire.append(RACINE).append("\\").append(ressource.getNom_repertoire());
		File dir = new File(tmpNomRepertoire.toString());
		File[] childs = dir.listFiles(); // dia chi tuyet doi den file

		for (File file : childs) {
			Fichier tmpFichier = new Fichier();
			tmpFichier.setNom_fichier(file.getName()); // sauvegarder nom de fichier
			tmpFichier.setId_parent(ressource.getId_repertoire()); // idParent=idRepertoire
			if (file.isDirectory()) {
				tmpFichier.setDossier();// un dossier
			}

			tmpFichier.setChemin_acces("\\" + ressource.getNom_repertoire() + "\\" + file.getName());
			listFichier.add(tmpFichier);
		}
		return listFichier;
	}

//	public void creerListFichierEntier(List<Fichier> listFichier, String nomDossier, int niveau) {
////		RepertoireDB reper = new RepertoireDB();
////		List<Repertoire> listRepertoire = reper.getRepertoire();
////		Repertoire ressource = listRepertoire.get(1);
//
//		StringBuilder tmpNomRepertoire = new StringBuilder();
//		tmpNomRepertoire.append(RACINE).append("\\").append(nomDossier);
//		File dir = new File(tmpNomRepertoire.toString());
//		File[] childs = dir.listFiles(); // dia chi tuyet doi den file
//
//		for (File file : childs) {
//			Fichier tmpFichier = new Fichier();
//			tmpFichier.setNom_fichier(file.getName()); // sauvegarder nom de fichier
////			tmpFichier.setId_parent(ressource.getId_repertoire()); // idParent=idRepertoire
//			if (file.isDirectory()) {
//				tmpFichier.setDossier();// un dossier
//			}
//
//			tmpFichier.setChemin_acces("\\" + ressource.getNom_repertoire() + "\\" + file.getName());
//			listFichier.add(tmpFichier);
//		}
//		return listFichier;
//	}

}
