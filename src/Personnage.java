import java.util.Scanner;

public class Personnage {
	// Attributs
	private int pv;
	private int potion;
	private char avatar;
	private String name;
	Scanner scp = new Scanner(System.in);

	// Constructeur
	public Personnage() {
		pv = 10;
		potion = 1;
		System.out.println("Veuillez entrer votre nom : ");
		name = scp.next();
	}

	// Constructeur multijoueur
	public Personnage(char avatar) {
		pv = 10;
		potion = 1;
		this.setAvatar(avatar);
	}

	// getter et setteur
	public String getName() {
		return name;
	}

	public void setName(String nom) {
		name = nom;
	}

	public int getPv() {
		return pv;
	}

	public void setPv(int life) {
		pv = life;
	}

	public int getPotion() {
		return potion;
	}
	 
	public void setPotion(int po) {
		System.out.println("Vous avez trouvé " + po + " potion(s) !");
		potion += po;
	}
	// methodes
	
	public void remplirInventaire() {
		int inventaire = getPotion()+1;
	}

	public String toString() {
		return "Vos caractéristiques :\nNombre de PV: " + pv + "\nNombre de potion: " + potion +  " et votre nom: "+getAvatar()+"\n----------------";
	}

	public void potion() {
		if (potion > 0 && pv < 10) {
			if (pv >= 7) {
				potion -= 1;
				System.out.println("Vous utilisez une potion : +" + (10 - pv) + " PV");
				pv += (10 - pv);
				System.out.println("Vous avez maintenant " + pv + "PV");
				System.out.println("Il vous reste "+potion+" potion(s)");
			} else {
				potion -= 1;
				System.out.println("Vous utilisez une potion : +4 PV");
				pv += 4;
				System.out.println("Vous avez maintenant " + pv + "PV");
			}
		} else if (pv == 10) {
			System.out.println("Vous avez déjà le maximum de PV");
		} else {
			System.out.println("Vous n'avez pas de potion...");
		}
	}

	public void piege() {
		if (pv > 3) {
			System.out.println("Vous êtes tombés sur un piège : -3 PV");
			pv -= 3;
			System.out.println("Il vous reste " + pv + "PV");
		} else {
			System.out.println("Vous êtes tombés sur un piège : -" + pv + "PV");
			pv -= pv;
		}
	}

	public char getAvatar() {
		return avatar;
	}

	public void setAvatar(char avatar) {
		this.avatar = avatar;
	}
}
