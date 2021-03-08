import java.util.Random;

public class Map {

	private String[][] map;

	public Map() { // Je me suis aidé de cette vidéo--> https://youtu.be/QVXM9YeO7rw
		System.out.println("Voici la carte ---->");
		map = new String[15][15];
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				map[i][j] = "-";
				map[7][7] = "P";
				System.out.print(" " + map[i][j]);
			}
			System.out.println(" ");
			/*if (i != 6) {
				Random rand = new Random();
				map[rand.nextInt(14)][rand.nextInt(14)] = "M";
			}*/
		}
	}

	public void objet(int a, int b, String c) { // a etant les lignes et b les colonnes
		if (a < 0 || b < 0 || a > 14 || b > 14) {
			System.out.println("Attention, vous voulez mettre un objet en dehors de la carte");
		} else {
			if (map[a][b] == "-") {
				map[a][b] = c;
			} else {
				System.out.println("Attention, l'emplacement est déja pris!");
			}
		}
	}

	public void carte() {
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				map[i][j] = "-";
				System.out.print(" " + map[i][j]);
			}
			System.out.print(" ");
		}
	}
}
