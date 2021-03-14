import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collection;
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
	public Map() { // Nous nous sommes aidé de cette vidéo--> https://youtu.be/QVXM9YeO7rw
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

	// placer un objet
	public void objet(int a, int b, char c) { // a etant les lignes et b les colonnes
		a -= 1;
		b -= 1;
		if (a < 0 || b < 0 || a > ligne || b > colonne) {
			throw new ArrayIndexOutOfBoundsException(
					"Attention, vous essayez de mettre un objet en dehors de la carte");
		} else {
			if (map[a][b] == 'X' || map[a][b] == ' ') {
				map[a][b] = c;
			} else {
				System.out.println("Attention, l'emplacement est déja pris à la " + (a + 1) + "ème ligne et la "
						+ (b + 1) + "ème colonne");
			}
		}
	}

	public void casesPieges(Personnage perso, Map carte) {
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
		//penser à retirer ce for
		for (String elem : trap) {
			System.out.print("["+elem+"]");
		}
		int nbrpieges = trap.size();
		System.out.println("Attention, "+ nbrpieges +" pièges sont cachés dans le donjon...");
	}

	// la partie
	public void jeu(Personnage perso, Map carte) {
		while (map[1][1] != 'O' || perso.getPv() != 0) {
			System.out.println(perso);
			saisieClavier(perso, carte);
		}
		if (perso.getPv() == 0) {
			System.out.println("VOUS ETES MORT !!!");
		} else {
			System.out.println("Vous avez réussi à sortir du Donjon !\nVICTOIRE!!!");
		}
	}

	// Pour haut, bas, droite, gauche avec quelques modifications réalisées :
	// https://youtu.be/7CHhq2MXWbk
	public void haut(Personnage perso, Map carte) {
		char pers;
		for (int i = 0; i < ligne; i++) {
			for (int j = 0; j < colonne; j++) {
				if (map[i][j] == 'O') {
					if ((i - 1) >= 0) {
						pers = map[i][j];
						map[i][j] = ' ';
						map[i - 1][j] = pers;
						String posJoueur = String.valueOf(i) + String.valueOf(j);
						casesPieges(perso, carte);
						Iterator<String> it = trap.iterator();
						boolean y = it.hasNext();
						while (y) {
							
							if(posJoueur.equals(it.next())){
								y = false;}
							
							String nxt = it.next();
							
							}

					}
				} else {
					System.err.println("Vous voulez vous déplacer en dehors de la carte !");
				}
			}
		}
	}

	

	public void gauche(Personnage perso, Map carte) {
		char pers;
		for (int i = 0; i < ligne; i++) {
			for (int j = 0; j < colonne; j++) {
				if (map[i][j] == 'O') {
					if ((j - 1) >= 0) {
						pers = map[i][j];
						map[i][j] = ' ';
						map[i][j - 1] = pers;
					} else {
						System.err.println("Vous voulez vous déplacer en dehors de la carte !");
					}
				}
			}
		}
	}

	public void bas(Personnage perso, Map carte) {
		char pers;
		for (int i = 0; i < ligne; i++) {
			for (int j = 0; j < colonne; j++) {
				if (map[i][j] == 'O') {
					if ((i + 1) < ligne) {
						pers = map[i][j];
						map[i + 1][j] = pers;
						map[i][j] = ' ';
						i += 1;
					} else {
						System.err.println("Vous voulez vous déplacer en dehors de la carte !");
					}
				}
			}
		}
	}

	public void droite(Personnage perso, Map carte) {
		char pers;
		for (int i = 0; i < ligne; i++) {
			for (int j = 0; j < colonne; j++) {
				if (map[i][j] == 'O') {
					if ((j + 1) < colonne) {
						pers = map[i][j];
						map[i][j] = ' ';
						map[i][j + 1] = pers;
						j += 1;
					} else {
						System.err.println("Vous voulez vous déplacer en dehors de la carte !");
					}
				}
			}
		}
	}

	public void saisieClavier(Personnage perso, Map carte) {
		System.out.println("Dans quelle direction voulez vous aller?");
		System.out.println("z = vers le haut");
		System.out.println("s = vers le bas");
		System.out.println("q = vers la gauche");
		System.out.println("d = vers la droite");
		System.out.println("e = prendre une potion");
		String a = sc.next();
		if (a.equals("z")) {
			haut(perso, carte);
			carte.affichage();
		} else if (a.equals("q")) {
			gauche(perso, carte);
			carte.affichage();
		} else if (a.equals("s")) {
			bas(perso, carte);
			carte.affichage();
		} else if (a.equals("d")) {
			droite(perso, carte);
			carte.affichage();
		} else if (a.equals("e")) {
			perso.potion();
			carte.affichage();
			System.out.println();
		} else {
			carte.affichage();
			System.err.println("Vous n'avez pas saisi une bonne lettre");
		}
	}
}