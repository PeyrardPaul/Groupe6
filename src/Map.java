
public class Map {

	public Map() { //Je me suis aidé de cette vidéo-->  https://youtu.be/QVXM9YeO7rw
		System.out.println("Voici la carte ---->");
		String[][] map = new String[15][15];
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				map[i][j] = "-";
				if (map[7][7] == "-") {
					map[7][7] = "P";
				}
				System.out.print(" " + map[i][j]);
			}
			System.out.println(" ");
		}
	}
}
