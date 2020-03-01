package fr.test.java.modele;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

//CTRL + SHIFT + O pour g�n�rer les imports n�cessaires

public class Fenetre extends JFrame {
	private JTree arbre;

	public Fenetre() {
		this.setSize(350, 700);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Les arbres");
		// On invoque la m�thode de construction de notre arbre
		buildTree();

		this.setVisible(true);
	}

	private void buildTree() {
		// Cr�ation d'une racine
		DefaultMutableTreeNode racine = new DefaultMutableTreeNode("Racine");

		// Nous allons ajouter des branches et des feuilles � notre racine
		for (int i = 1; i < 12; i++) {
			DefaultMutableTreeNode rep = new DefaultMutableTreeNode("Noeud n�" + i);

			// S'il s'agit d'un nombre pair, on rajoute une branche
			if ((i % 2) == 0) {
				// Et une branche en plus ! Une !
				for (int j = 1; j < 5; j++) {
					DefaultMutableTreeNode rep2 = new DefaultMutableTreeNode("Fichier enfant n�" + j);
					// Cette fois, on ajoute les feuilles
					for (int k = 1; k < 5; k++)
						rep2.add(new DefaultMutableTreeNode("Sous-fichier enfant n�" + k));
					rep.add(rep2);
				}
			}
			// On ajoute la feuille ou la branche � la racine
			racine.add(rep);
		}
		// Nous cr�ons, avec notre hi�rarchie, un arbre
		arbre = new JTree(racine);

		// Que nous pla�ons sur le ContentPane de notre JFrame � l'aide d'un scroll
		this.getContentPane().add(new JScrollPane(arbre));
	}

	public static void main(String[] args) {
		Fenetre fen = new Fenetre();
	}
}