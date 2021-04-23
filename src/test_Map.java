import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Random;
import java.util.Scanner;

public class test_Map {
	private int ligne;
	private int colonne;
	private char[][] map;
	static ArrayList<String> trap = new ArrayList<String>();
	static ArrayList<String> potion = new ArrayList<String>();
	Random rand = new Random();
	Scanner sc = new Scanner(System.in);
	int min = 0;
	int max = 14;
	int a = rand.nextInt(max - min) + min;
	int b = rand.nextInt(max - min) + min;

	public static void main(String[] args) {

		Map map = new Map();
		System.out.println(map.getMap());
		Personnage perso = new Personnage();
		// construction + affichage

		map.jeu(perso, map, 'O');
		System.out.println(trap);
		System.out.println(potion);
	}

	// affichage ok
	// vérification du nombre de pièges

	public void casesPieges() {
		int p = 1;

		while (p <= 55) {
			int alea = rand.nextInt(max - min) + min;
			int aleadeux = rand.nextInt(max - min) + min;
			if ((alea != a && aleadeux != b) && (alea != 1 && aleadeux != 1) && (alea != 0 || aleadeux != 0)) {
				p += 1;
				String.valueOf(alea);
				String.valueOf(aleadeux);
				String posTrap = String.valueOf(alea) + String.valueOf(aleadeux);
				trap.add(posTrap);
			}

			
		}
		

	}

	public void casesPotion() {
		int p = 1;

		while (p <= 20) {
			int alea = rand.nextInt(max - min) + min;
			int aleadeux = rand.nextInt(max - min) + min;
			if ((alea != a && aleadeux != b) && (alea != 1 && aleadeux != 1)&& (alea != 0 || aleadeux != 0)) {
				p += 1;
				String.valueOf(alea);
				String.valueOf(aleadeux);
				String posPotion = String.valueOf(alea) + String.valueOf(aleadeux);
				potion.add(posPotion);
			}
		}

		int nbrpotions = potion.size();
		System.out.println(nbrpotions + " potions sont disponibles pour soigner vos blessures...\n");
		System.out.println(potion);
	}
	

}











