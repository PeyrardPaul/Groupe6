import java.util.Scanner;

public class Personnage {
	//Attributs
	private int pv;
	private int potion;
	private String name;
	Scanner scp = new Scanner(System.in);

	//Constructeur
	public Personnage() {
		pv = 20;
		potion = 1;
		System.out.println("Veuillez entrer votre nom : ");
		name = scp.next();
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
	
	public int getPotion() {
		return potion;
	}

	public void setPotion(int po) {
		System.out.println("Vous avez trouvé "+po+" potion(s) !");
		potion +=po;
	}
	//methodes
	
	public String toString() {
		return "Bonjour "+name+", voici vos caractéristiques :\nNombre de PV: "+pv+"\nNombre de potion: "+potion+"\n----------------";
	}
	
	public void potion() {
		if (potion>0 && pv <20) {
			if (pv>=17) {
				potion -= 1;
				System.out.println("Vous utilisez une potion : +"+(20-pv)+" PV");
				pv += (20-pv);
				System.out.println("Vous avez maintenant " + pv + "PV");
			}else {
				potion -= 1;
				System.out.println("Vous utilisez une potion : +4 PV");
				pv += 4;
				System.out.println("Vous avez maintenant " + pv + "PV");
			}
		} else if (pv == 20) {
			System.out.println("Vous avec déjà le maximum de PV");
		} else {
			System.out.println( "Vous n'avez pas de potion...");
		}	
	}

	public void piege() {
		if (pv>3) {
			System.out.println("Vous êtes tombés sur un piège : -3 PV");
			pv -= 3;
			System.out.println("Il vous reste " + pv + "PV");
		}else {
			pv -=3;
			System.out.println("VOUS ETES MORT !!!");
		}
	}
}
