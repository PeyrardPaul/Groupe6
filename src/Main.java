//import java.util.Random;
public class Main {
	public static void main(String[] args) {
		/*
		 * int p = 1; Random rand = new Random(); int min = 0; int max = 11;
		 * while(p<=50) { int alea = rand.nextInt(max - min) + min; int aleap =
		 * rand.nextInt(max - min) + min; System.out.print(alea+"  ");
		 * System.out.print(aleap+"  "); p+=1; } }
		 */
		Map map = new Map();
		Personnage perso = new Personnage();
		// map.casesPieges(perso,map);
		// map.deplacerMultiple(perso, map, 3);
		map.jeu(perso, map, 'O');

	}
}
