import java.util.Scanner;

public class Personnage {
	//Attributs
	private int pv;
	private int potion;
	private String name;
	public static int nbjoueur = 0;

	//Constructeur
	public Personnage() {
		pv = 20;
		potion = 1;
		Scanner sc = new Scanner(System.in);
		System.out.println("Veuillez entrer votre nom : ");
		name = sc.next();
		System.out.println("Bonjour "+name+", voici vos caractéristiques :\nNombre de PV: "+pv+"\nNombre de potion: "+potion+"\n----------------");
	}

	//getter et setteur
	public String getName() {
		return name;
	}

	public void setName(String nom){
		name = nom;
	}

	public int getPv() {
		return pv;
	}

	public void setPv(int life){
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
}
