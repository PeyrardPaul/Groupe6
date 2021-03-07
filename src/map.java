
public class map {
	public static void main(String[] args) {
	// déclarer la matrice
	int m = 15;
	int n = 15;
	String[][] a = new String[m][n];
	for(int i = 0;i<m;i++){
		for (int j = 0; j < n; j++) {
			a[i][j] = "X"; // System.out.print(String.format("Entrez a[%d][%d] : ", i, j));
			// sc.nextInt() ;
		}
	}
	for(int i = 0;i<a.length;i++){
		for (int j = 0; j < a[0].length; j++) {
			System.out.print("|" + a[i][j]);
		}
		System.out.println("|");
	}

}}
