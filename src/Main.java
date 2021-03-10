
public class Main {
	public static void main(String[] args) {
		Map map = new Map();
		
		map.objet(8,8,'P');
		map.affichage();
		Personnage perso = new Personnage();
		map.bouger(perso);
	}
}
