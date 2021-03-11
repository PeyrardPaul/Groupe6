
public class Main {
	public static void main(String[] args) {
		Map map = new Map();
		map.affichage();
		Personnage perso = new Personnage();
		perso.piege();
		map.jeu(perso, map);
	}
}
