
public class test_Map {
	

	public static void main(String[] args) {
		
		Map map = new Map();
		System.out.println(map.getMap());
		Personnage perso = new Personnage();
		// map.casesPieges(perso,map);
		// map.deplacerMultiple(perso, map, 3);
		map.jeu(perso, map, 'O');

	}
}
