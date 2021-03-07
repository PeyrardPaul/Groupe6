import java.awt.Color;
import java.util.Scanner;

import javax.swing.JLabel;

public class Personnage {
	//Attributs
	private int pv;
	private int potion;
	private String name;
	public static int nbjoueur = 0;

	//Constructeur
	public Personnage() throws PersonnageException {
		pv = 20;
		potion = 1;
		Scanner sc = new Scanner(System.in);
		System.out.println("Veuillez entrer votre nom : ");
		name = sc.next();
		System.out.println("Vous vous appelez " + name + " et vous commencez avec " + pv + " PV");
	}

	//getter et setteur
	public String getName() {
		return name;
	}

	public void setName(String nom) throws PersonnageException {
		name = nom;
	}

	public int getPv() {
		return pv;
	}

	public void setPv(int life) throws PersonnageException {
		pv = life;
	}

	//methodes
	
	public String potion() {
		if (potion>0 && pv <20) {
			if (pv>=17) {
				System.out.println("Vous utilisez une potion : +"+(20-pv)+" PV");
				pv += (20-pv);
				return "Vous avez maintenant " + pv + "PV";
			}else {
				potion -= 1;
				System.out.println("Vous utilisez une potion : +4 PV");
				pv += 4;
				return "Vous avez maintenant " + pv + "PV";
			}
		}else if (pv == 20) {
			return "Vous avec déjà le maximum de PV";
		}else {
			return "Vous n'avez pas de potion...";
		}	
	}

	public String piege() {
		if (pv>3) {
			System.out.println("Vous êtes tombés sur un piège : -3 PV");
			pv = pv - 3;
			return "Il vous reste " + pv + "PV";
		}else {
			return "VOUS ETES MORT !!!";
		}
	}
	
	public String toString() {
		return "Vous vous appelez " + getName() + " et vous commencez avec " + getPv() + " de vie";
	}
	
	public static void main(String[] args) throws PersonnageException {
	Personnage un = new Personnage();
	System.out.println(un.piege());
	System.out.println(un.potion());
	}
}
