import java.util.Scanner;

public class map {
	public static void main(String[] args) {
		// déclarer la matrice
		Scanner sc = new Scanner(System.in);
		System.out.println("Bienvenue dans DONJONS  ET DRAGONS");
		System.out.println("Pour commencer la partie tapez: 'aventure'");
		int m = 15;
		int n = 15;
		if (sc.next().equals("aventure")) {
			String[][] a = new String[m][n];
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					a[i][j] = "X"; // System.out.print(String.format("Entrez a[%d][%d] : ", i, j));
					// sc.nextInt() ;
				}
			}
			for (int i = 0; i < a.length; i++) {
				for (int j = 0; j < a[0].length; j++) {
					System.out.print("|"+a[i][j]);
				}
				System.out.println("|");
			}
			// fermez scanner
		} else {
			System.err.println("Tu as droit à une deuxième tentative...");
			if (sc.next().equals("aventure")) {
				String[][] a = new String[m][n];
				for (int i = 0; i < m; i++) {
					for (int j = 0; j < n; j++) {
						a[i][j] = "X"; // System.out.print(String.format("Entrez a[%d][%d] : ", i, j));
						// sc.nextInt() ;
					}
				}
				for (int i = 0; i < a.length; i++) {
					for (int j = 0; j < a[0].length; j++) {
						System.out.print("|"+a[i][j]);
					}
					System.out.println("|");
				}
			} else {
				System.err.println("Ok j'ai compris tu veux pas jouer..");
			}
			sc.close();
		}
	}
}