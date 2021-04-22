import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;

public class Map {
	private int ligne;
	private int colonne;
	private char[][] map;
	ArrayList<String> trap = new ArrayList<String>();
	Random rand = new Random();
	Scanner sc = new Scanner(System.in);
	int min = 0;
	int max = 14;
	int a = rand.nextInt(max - min) + min;
	int b = rand.nextInt(max - min) + min;

	// constructeur
	public Map() { // Nous nous sommes aidés de cette vidéo--> https://youtu.be/QVXM9YeO7rw
		System.out.println("Informations :\nMonstre = M\nMur = #\nPiege = T\n\nVoici la carte ---->");
		ligne = 15;
		colonne = 15;
		setMap(new char[ligne][colonne]);

		for (int i = 0; i < ligne; i++) {
			for (int j = 0; j < colonne; j++) {
				getMap()[i][j] = 'X';
			}
		}

		getMap()[a][b] = 'O';
		while (a == 1 && b == 1) {
			getMap()[a][b] = 'X';
			a = rand.nextInt(max - min) + min;
			b = rand.nextInt(max - min) + min;
			getMap()[a][b] = 'O';
		}
		affichage();
		casesPieges();
	}

	// constructeur surchage pour multiplayer
	public Map(Boolean multiplayer) {

		// System.out.println("Informations :\nMonstre = M\nMur = #\nPiege = T\n\nVoici
		// la carte ---->");
		ligne = 12;
		colonne = 12;
		this.map = new char[ligne][colonne];

		ligne = 15;
		colonne = 15;
		this.setMap(new char[ligne][colonne]);

		for (int i = 0; i < ligne; i++) {
			for (int j = 0; j < colonne; j++) {
				this.getMap()[i][j] = 'X';
			}
		}
		// affichage();

	}

	public int getLigne() {
		return ligne;
	}

	public void setLigne(int ligne) {
		this.ligne = ligne;
	}

	public int getColonne() {
		return colonne;
	}

	public void setColonne(int colonne) {
		this.colonne = colonne;

	}

	public void casesPieges() {
		int p = 1;

		while (p <= 55) {
			int alea = rand.nextInt(max - min) + min;
			int aleadeux = rand.nextInt(max - min) + min;
			if ((alea != a && aleadeux != b) && (alea != 1 && aleadeux != 1)) {
				p += 1;
				String.valueOf(alea);
				String.valueOf(aleadeux);
				String posTrap = String.valueOf(alea) + String.valueOf(aleadeux);
				trap.add(posTrap);
			}
		}
		// penser à retirer ce for ?????
		/*
		 * for (String elem : trap) { System.out.print("[" + elem + "]"); }
		 */
		int nbrpieges = trap.size();
		System.out.println("Attention, " + nbrpieges + " pièges sont cachés dans le donjon...\n");

	}

	public void addPersonnage(char personnageLetter) {
		while (true) {
			a = rand.nextInt(max - min) + min;
			b = rand.nextInt(max - min) + min;

			System.out.println("Ajout du personnage " + personnageLetter + ":");

			if (this.map[a][b] == 'X') {
				this.map[a][b] = personnageLetter;
				break;
			}
		}
	}

	// affichage
	public void affichage() {
		System.out.println("Voici la carte ---->");
		for (int i = 0; i < ligne; i++) {
			for (int j = 0; j < colonne; j++) {
				System.out.print(" " + getMap()[i][j]);
			}
			System.out.println(" ");
		}
	}

	public String getAffichage() {
		String t = "";
		for (int i = 0; i < ligne; i++) {
			for (int j = 0; j < colonne; j++) {
				t = t + " " + this.getMap()[i][j];
			}
			t = t + "\n";
		}
		return t;

	}

	// placer un objet
	public void objet(int a, int b, char c) { // a= lignes et b=colonnes
		a -= 1;
		b -= 1;
		if (a < 0 || b < 0 || a > ligne || b > colonne) {
			throw new ArrayIndexOutOfBoundsException(
					"Attention, vous essayez de mettre un objet en dehors de la carte");
		} else {
			if (getMap()[a][b] == 'X' || getMap()[a][b] == ' ') {
				getMap()[a][b] = c;
			} else {
				System.out.println("Attention, l'emplacement est déjà pris à la " + (a + 1) + " ème ligne et la "
						+ (b + 1) + " ème colonne");
			}
		}
	}

	// la partie
	public void jeu(Personnage perso, Map carte, char personnageLetter) {

		while (true) {
			saisieClavierSolo(perso, carte, personnageLetter);
			if ((map[1][1] == personnageLetter) || (perso.getPv() == 0)) {
				boolean b = true;
				while (b) {
					// carte.saisieClavier(perso, carte, personnageLetter);
					if ((carte.getMap()[1][1] == personnageLetter) || (perso.getPv() == 0)) {
						if (perso.getPv() == 0) {
							System.out.println("VOUS ETES MORT !!!");
							break;
						}
						if (getMap()[1][1] == personnageLetter) {
							System.out.println("Vous avez réussi à sortir du Donjon !\nVICTOIRE!!!");
							break;
						}
						b = false;
					}
				}
			}
		}
	}

	public void multiPlayer() {
		System.out.println(getMap());
	}

	/*
	 * public void deplacerMultiple(Personnage perso, Map carte, int a) {
	 * System.out.println("Dans quelle direction voulez vous aller?");
	 * System.out.println("z = vers le haut");
	 * System.out.println("s = vers le bas");
	 * System.out.println("q = vers la gauche");
	 * System.out.println("d = vers la droite");
	 * System.out.println("e = prendre une potion"); int z = 0; int s = 0; int d =
	 * 0; int q = 0; String b; for (int i =0; i<a; i++) { b = sc.next(); if
	 * (b.equals("q")) { q -=1; //deplacer(perso, carte, 0, q); } if (b.equals("z"))
	 * { z-=1; //deplacer(perso, carte, z, 0); } if (b.equals("s")) { s+=1;
	 * //deplacer(perso, carte, s, 0); }
	 * 
	 * if (b.equals("d")) { d+=1; //deplacer(perso, carte, 0, d); } deplacer(perso,
	 * carte, (z+s), (d+q)); } carte.affichage(); }
	 */

	// On va utiliser une seule fonction pour le deplacement, on ajoute les
	// arguments de la foncton a et b, a pour l'axe vertical, et b pour l'axe
	// horizontal

	public void deplacer(Personnage perso, Map carte, int a, int b, char personnageLetter) {
		char pers;

		// cette variable est vraie quand la position du perso est trouvée dans la map
		boolean positionPersoTrouver = false;
		for (int i = 0; i < ligne; i++) {
			for (int j = 0; j < colonne; j++) {
				if (getMap()[i][j] == personnageLetter) {
					positionPersoTrouver = true;

					// on test pour i et j si égal à 0 pour dire que le joueur est en dehors du
					// terrain
					if ((i + a) >= 0 && (j + b) >= 0 && (i + a) < 15 && (j + b) < 15) {

						pers = getMap()[i][j];
						if ((getMap()[i + a][j + b] != 'A') && (getMap()[i + a][j + b] != 'B')
								&& (getMap()[i + a][j + b] != 'C') && (getMap()[i + a][j + b] != 'D')
								&& (getMap()[i + a][j + b] != 'E') && (getMap()[i + a][j + b] != 'F')
								&& (getMap()[i + a][j + b] != 'G') && (getMap()[i + a][j + b] != 'H')) {
							getMap()[i][j] = ' ';
							getMap()[i + a][j + b] = pers;
						} else {
							System.err.println("Vous voulez vous déplacer sur un autre joueur");
						}
						String posJoueur = String.valueOf(i) + String.valueOf(j);

						// perso sur piège ou pas

						Iterator<String> it = trap.iterator();

						// z est vrai tant que la position du joueur n'est pas celle d'un piège
						boolean z = true;
						String nxt;

						while (it.hasNext() && z) {

							nxt = it.next();

							if (posJoueur.equals(nxt)) {
								z = false;
								it.remove();
								perso.piege();
							}
							/*
							 * int lastItem = trap.size()-1; if(nxt.equals(lastItem)) {
							 * 
							 * if(false) { y = false; }
							 */
						}

					} else {
						System.err.println("Vous voulez vous déplacer en dehors de la carte !");
					}
					// pas besoin de parcourir le reste de la map quand on a trouvé la position du
					// joueur

				}
				// quand on a trouvé la position du personnage on ne va plus parcourir toute la
				// map
				if (positionPersoTrouver) {
					break;
				}
			}
			// quand on a trouvé la position du personnage on ne va plus parcourir toute la
			// map
			if (positionPersoTrouver) {
				break;
			}
		}
	}

	public void saisieClavierMulti(Personnage perso, Map carte, char personnageLetter, String a) {

		/*
		 * System.out.println("Dans quelle direction voulez vous aller?");
		 * System.out.println("z = vers le haut");
		 * System.out.println("s = vers le bas");
		 * System.out.println("q = vers la gauche");
		 * System.out.println("d = vers la droite");
		 * System.out.println("e = prendre une potion");
		 */

		/*
		 * System.out.println("Dans quelle direction voulez vous aller?");
		 * System.out.println("z = vers le haut");
		 * System.out.println("s = vers le bas");
		 * System.out.println("q = vers la gauche");
		 * System.out.println("d = vers la droite");
		 * System.out.println("e = prendre une potion");
		 */

		if (a.equals("z")) {
			// deplacement vers le haut
			deplacer(perso, carte, -1, 0, personnageLetter);
			carte.affichage();
		} else if (a.equals("q")) {
			// deplacement gauche
			deplacer(perso, carte, 0, -1, personnageLetter);
			carte.affichage();
		} else if (a.equals("s")) {
			// deplacement bas
			deplacer(perso, carte, +1, 0, personnageLetter);
			carte.affichage();
		} else if (a.equals("d")) {
			// deplacement droite
			deplacer(perso, carte, 0, 1, personnageLetter);
			carte.affichage();
		} else if (a.equals("e")) {
			perso.potion();
			carte.affichage();

		} else {
			carte.affichage();

			System.err.println("Vous n'avez pas saisi de lettre valide");

		}
	}

	public char[][] getMap() {
		return map;
	}

	public void setMap(char[][] map) {
		this.map = map;
	}

	public void saisieClavierSolo(Personnage perso, Map carte, char personnageLetter) {

		
		 System.out.println("Dans quelle direction voulez vous aller?");
		 System.out.println("z = vers le haut");
		 System.out.println("s = vers le bas");
		 System.out.println("q = vers la gauche");
		 System.out.println("d = vers la droite");
		 System.out.println("e = prendre une potion");
		
		 String a = sc.next();
		if (a.equals("z")) {
			// deplacement vers le haut
			deplacer(perso, carte, -1, 0, personnageLetter);
			carte.affichage();
		} else if (a.equals("q")) {
			// deplacement gauche
			deplacer(perso, carte, 0, -1, personnageLetter);
			carte.affichage();
		} else if (a.equals("s")) {
			// deplacement bas
			deplacer(perso, carte, +1, 0, personnageLetter);
			carte.affichage();
		} else if (a.equals("d")) {
			// deplacement droite
			deplacer(perso, carte, 0, 1, personnageLetter);
			carte.affichage();
		} else if (a.equals("e")) {
			perso.potion();
			carte.affichage();

		} else {
			carte.affichage();

			System.err.println("Vous n'avez pas saisi de lettre valide");

		}
	}
}