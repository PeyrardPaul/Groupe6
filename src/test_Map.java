import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Random;
import java.util.Scanner;

public class test_Map {
	private int ligne;
	private int colonne;
	private char[][] map;
	ArrayList<String> trap = new ArrayList<String>();
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
		//construction + affichage
		
		map.jeu(perso, map, 'O');}
		// affichage ok 
		//v�rification du nombre de pi�ges 
		//parcours de l'arraylist avec objet enumeration (source:http://www.codeurjava.com/2015/06/comment-parcourir-un-arraylist-en-java.html)
		public void casesPieges() {
			int p = 1;

			while (p <= 55) {
				int alea = rand.nextInt(max - min) + min;
				int aleadeux = rand.nextInt(max - min) + min;
				if ((alea != a && aleadeux != b) && (alea != 1 && aleadeux != 1)) {
					p += 1;
					String.valueOf(alea);
					String.valueOf(aleadeux);
					String posTrap = String.valueOf(alea) + String.valueOf(aleadeux);
					trap.add(posTrap);
				}
			}

			
			 System.out.println(trap);
			 
			int nbrpieges = trap.size();
			System.out.println("Attention, " + nbrpieges + " pi�ges sont cach�s dans le donjon...\n");

		

		//ok pour la construction et l'affichage de la map
		
		

	}
}
