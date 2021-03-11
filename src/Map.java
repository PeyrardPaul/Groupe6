import java.util.Scanner;

public class Map {
	private int ligne;
	private int colonne;
	private char[][] map;
	Scanner sc = new Scanner(System.in);

	public Map() { // Nous nous sommes aidï¿½ de cette vidï¿½o--> https://youtu.be/QVXM9YeO7rw
		System.out.println("Informations :\nMonstre = M\nMur = #\nPotion = +\nPiege = T\n\nVoici la carte ---->");
		ligne = 15;
		colonne = 15;
		map = new char[ligne][colonne];

		for (int i = 0; i < ligne; i++) {
			for (int j = 0; j < colonne; j++) {
				map[i][j] = '-';
				map[7][7] = 'P';
			}
		}
	}

	public void affichage() {
		for (int i = 0; i < ligne; i++) {
			for (int j = 0; j < colonne; j++) {
				System.out.print(" " + map[i][j]);
			}
			System.out.println(" ");
		}
	}

	public void objet(int a, int b, char c) { // a etant les lignes et b les colonnes
		a -= 1;
		b -= 1;
		if (a < 0 || b < 0 || a > ligne || b > colonne) {
			throw new ArrayIndexOutOfBoundsException(
					"Attention, vous essayez de mettre un objet en dehors de la carte");
		} else {
			if (map[a][b] == '-') {
				map[a][b] = c;
			} else {
				System.out.println("Attention, l'emplacement est déja pris à la " + (a + 1) + "ème ligne et la "
						+ (b + 1) + "ème colonne");
			}
		}
	}

	public void jeu(Personnage perso, Map carte) {
		for (int i = 0; i != 3; i++) {
			saisieClavier(perso, carte);
		}
	}

	public void saisieClavier(Personnage perso, Map carte) {
		System.out.println("Dans quelle direction voulez vous aller?");
		System.out.println("z = vers le haut");
		System.out.println("s = vers le bas");
		System.out.println("q = vers la gauche");
		System.out.println("d = vers la droite.");
		System.out.println("e = prendre une potion");
		String a = sc.next();
		if (a.equals("z")) {
			char pers;
			for (int i = 0; i < ligne; i++) {
				for (int j = 0; j < colonne; j++) {
					if (map[i][j] == 'P') {
						if ((i - 1) >= 0) {
							pers = map[i][j];
							map[i][j] = '-';
							map[i - 1][j] = pers;
						} else {
							System.err.println("Vous voulez vous déplacer en dehors de la carte !");
						}
					}
				}
			}
			carte.affichage();
		} else if (a.equals("q")) {
			char pers;
			for (int i = 0; i < ligne; i++) {
				for (int j = 0; j < colonne; j++) {
					if (map[i][j] == 'P') {
						if ((j - 1) >= 0) {
							pers = map[i][j];
							map[i][j] = '-';
							map[i][j-1] = pers;
						} else {
							System.err.println("Vous voulez vous déplacer en dehors de la carte !");
						}
					}
				}
			}
			carte.affichage();
		} else if (a.equals("s")) {
			char pers;
			for (int i = 0; i < ligne; i++) {
				for (int j = 0; j < colonne; j++) {
					if (map[i][j] == 'P') {
						if ((i+1) <=(ligne)) {
							pers = map[i][j];
							map[i][j] = '-';
							map[i+1][j] = pers;
						}else {
							System.err.println("Vous voulez vous déplacer en dehors de la carte !");
						}
					}
				}
			}
			carte.affichage();
		} else if (a.equals("d")) {
			char pers;
			for (int i = 0; i < ligne; i++) {
				for (int j = 0; j < colonne; j++) {
					if (map[i][j] == 'P') {
						if ((j+1) <= colonne) {
							pers = map[i][j];
							map[i][j] = '-';
							map[i][j+1] = pers;
						} else {
							System.err.println("Vous voulez vous déplacer en dehors de la carte !");
						}
					}
				}
			}
			carte.affichage();
		} else if (a.equals("e")) {
			perso.potion();
		} else {
			System.err.println("Vous n'avez pas saisi une bonne lettre");
		}
	}

	/*
	 * public void toutLesObjets() { int i = 1; Random rand = new Random(); int min
	 * = 1; int max = 15; boolean y = true; while (i < 5) { while (y) { if
	 * (map[rand.nextInt(max - min) + min][rand.nextInt(max - min) + min] == '-') {
	 * map[rand.nextInt(max - min) + min][rand.nextInt(max - min) + min] = 'M'; y =
	 * false; }else { map[rand.nextInt(max - min) + min][rand.nextInt(max - min) +
	 * min] = map[rand.nextInt(max - min) + min][rand.nextInt(max - min) + min]; }
	 * }i++; } }
	 */
}