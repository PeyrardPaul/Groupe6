import java.util.Scanner;

public class map {
	public static void main(String[] args) {
		System.out.print("Saisir le nombre de lignes dans la matrice: ");
		Scanner sc = new Scanner(System.in);
		int m = sc.nextInt();

		System.out.print("Saisir le nombre de colonnes dans la matrice: ");
		int n = sc.nextInt();
		// déclarer la matrice
		String[][] a = new String[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				a[i][j] ="*"; // System.out.print(String.format("Entrez a[%d][%d] : ", i, j));
				// sc.nextInt();
			}
		}
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[0].length; j++) {
				System.out.print(a[i][j] + "\t");
			}
			System.out.println();
		}

		// fermez scanner
		sc.close();
	}
}