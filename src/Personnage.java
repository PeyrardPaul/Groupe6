
public class Personnage {
	private String name = "";
	private int pv = 10;
	private final int numerojoueur;
	public static int nbjoueur = 0;

	public Personnage(String name, int pv) throws PersonnageException {
		setName(name);
		setPv(pv);
		numerojoueur = ++nbjoueur;
	}

	public int getNumerojoueur() {
		return numerojoueur;
	}

	public String getName() {
		if (name == "")
			name = "Player" + String.valueOf(getNumerojoueur());
		return name;
	}

	public void setName(String name) throws PersonnageException {
		if (name == null)
			throw new PersonnageException("Attention, vous n'avez pas entrer votre nom");
		this.name = name;
	}

	public int getPv() {
		if (pv != 10)
			pv = 10;
		return pv;
	}

	public void setPv(int pv) throws PersonnageException {
		if (getPv() != 10)
			throw new PersonnageException("Attention, vous n'avez pas autant de vie qu'il ne le faut");
		this.pv = pv;
	}

	public String toString() {
		return "Vous vous appelez " + getName() + " et vous commencez avec " + getPv() + " de vie";
	}

	public String potion() {
		System.out.println("Vous avez trouvé une potion : +2 PV");
		pv = pv + 2;
		return "Vous avez donc maintenant " + pv + "PV";
	}

	public String piege() {
		System.out.println("Vous êtes tombés sur un piège : -3 PV");
		pv = pv - 3;
		return "Vous avez donc maintenant " + pv + "PV";
	}

	public static void main(String[] args) throws PersonnageException {
		Personnage un = new Personnage("Pierre", 10);
		System.out.println(un);
		Personnage deux = new Personnage("Marie", 10);
		System.out.println(deux);
		System.out.println(deux.potion());
		System.out.println(deux.piege());
	}
}
