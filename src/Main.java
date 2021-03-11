
public class Main {
	public static void main(String[] args) {
		Map map = new Map();
		map.affichage();
		Personnage perso = new Personnage();
		map.saisieClavier(perso, map);
	}
}
