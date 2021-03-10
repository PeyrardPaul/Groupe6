import java.util.Scanner;

public class Map {
	private int ligne;
	private int colonne;
	private char[][] map;


	public Map() { // Nous nous sommes aid� de cette vid�o--> https://youtu.be/QVXM9YeO7rw
		System.out.println("Informations :\nMonstre = M\nMur = #\nPotion = +\nPiege = T\n\nVoici la carte ---->");
		ligne = 15;
		colonne = 15;
		map = new char[ligne][colonne];

		for (int i = 0; i < ligne; i++) {
			for (int j = 0; j < colonne; j++) {
				map[i][j] = '-';
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
				System.out.println("Attention, l'emplacement est d�ja pris � la " + (a + 1) + "�me ligne et la "
						+ (b + 1) + "�me colonne");
			}
		}
	}
	
	public void saisieClavier(Personnage perso) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Dans quelle direction voulez vous aller?");
		System.out.println("z = vers le haut");
		System.out.println("s = vers le bas");
		System.out.println("q = vers la gauche");
		System.out.println("d = vers la droite.");
		if (sc.next() == "z") {
			System.out.println("haut");
		} else if (sc.next() == "q") {
			System.out.println("gauche");
		}else if (sc.next() == "s") {
			System.out.println("bas");
		}else if (sc.next() == "d") {
			System.out.println("droite");
		}else {
			System.out.println("Vous n'avez paas saisi une bonne lettre");
		}sc.close();
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
