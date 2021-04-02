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
	int max = 11;
	int a = rand.nextInt(max - min) + min;
	int b = rand.nextInt(max - min) + min;

	// constructeur
	public Map() { // Nous nous sommes aidés de cette vidéo--> https://youtu.be/QVXM9YeO7rw
		System.out.println("Informations :\nMonstre = M\nMur = #\nPiege = T\n\nVoici la carte ---->");
		ligne = 12;
		colonne = 12;
		map = new char[ligne][colonne];

		for (int i = 0; i < ligne; i++) {
			for (int j = 0; j < colonne; j++) {
				map[i][j] = 'X';
			}
		}

		map[a][b] = 'O';
		while (a == 1 && b == 1) {
			map[a][b] = 'X';
			a = rand.nextInt(max - min) + min;
			b = rand.nextInt(max - min) + min;
			map[a][b] = 'O';
		}
		affichage();
		casesPieges();
	}

	// constructeur surchage pour multiplayer
	public Map(Boolean multiplayer) {
		System.out.println("Informations :\nMonstre = M\nMur = #\nPiege = T\n\nVoici la carte ---->");
		ligne = 12;
		colonne = 12;
		this.map = new char[ligne][colonne];

		for (int i = 0; i < ligne; i++) {
			for (int j = 0; j < colonne; j++) {
				this.map[i][j] = 'X';
			}
		}
		affichage();
		
	}

	public void casesPieges() {
		int p = 1;

		while (p <= 15) {
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
		/*for (String elem : trap) {
			System.out.print("[" + elem + "]");
		}*/
		int nbrpieges = trap.size();
		System.out.println("Attention, " + nbrpieges + " pièges sont cachés dans le donjon...");

	}

	public void addPersonnage(char personnageLetter) {
		while (true) {
			a = rand.nextInt(max - min) + min;
			b = rand.nextInt(max - min) + min;
			System.out.println("Ajout du personnage "+personnageLetter+":");
			if (this.map[a][b] == 'X') {
				this.map[a][b] = personnageLetter;
				break;
			}

		}

	}

	// affichage
	public void affichage() {
		for (int i = 0; i < ligne; i++) {
			for (int j = 0; j < colonne; j++) {
				System.out.print(" " + map[i][j]);
			}
			System.out.println(" ");
		}
	}

	public String getAffichage() {
		String t = "";
		for (int i = 0; i < ligne; i++) {
			for (int j = 0; j < colonne; j++) {
				t = t + " " + this.map[i][j];
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
			if (map[a][b] == 'X' || map[a][b] == ' ') {
				map[a][b] = c;
			} else {
				System.out.println("Attention, l'emplacement est déjà pris à la " + (a + 1) + " ème ligne et la "
						+ (b + 1) + " ème colonne");
			}
		}
	}

	// la partie
	public void jeu(Personnage perso, Map carte, char personnageLetter) {
		boolean b;
		while (true) {
			System.out.println(perso);
			saisieClavier(perso, carte, personnageLetter);
			if ((map[1][1] == personnageLetter) || (perso.getPv() == 0)) {
				if (perso.getPv() == 0) {
					System.out.println("VOUS ETES MORT !!!");
					break;
				}
				if (map[1][1] == personnageLetter) {
					System.out.println("Vous avez réussi à sortir du Donjon !\nVICTOIRE!!!");
					break;
				}
				b = false;
			}
		}
	}

	public void multiPlayer() {
		System.out.println(map);
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
	// arguments de la foncton a et b, a pour dire l'axe haut et bas, et b pour
	// l'axe gauche et droite :

	public void deplacer(Personnage perso, Map carte, int a, int b, char personnageLetter) {
		char pers;

		// cette variable est vraie quand la position du perso est trouver dans la map
		boolean positionPersoTrouver = false;
		for (int i = 0; i < ligne; i++) {
			for (int j = 0; j < colonne; j++) {
				if (map[i][j] == personnageLetter) {
					positionPersoTrouver = true;

					// on test pour i et j si égal à 0 pour dire que le joueur est en dehors du
					// terrain
					if ((i + a) >= 0 && (j + b) >= 0 && (i + a) < 12 && (j + b) < 12) {

						pers = map[i][j];
						map[i][j] = ' ';
						map[i + a][j + b] = pers;
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

	public void saisieClavier(Personnage perso, Map carte, char personnageLetter) {
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
			System.err.println("Vous n'avez pas saisi lettre valide");
		}
	}
}