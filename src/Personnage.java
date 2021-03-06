
public class Personnage {
	private String name = "";
	private int vie = 10;
	private final int numerojoueur;
	public static int nbjoueur = 0;

	public Personnage(String name, int vie) throws PersonnageException {
		setName(name);
		setVie(vie);
		numerojoueur = ++nbjoueur;	
	}

	
	public int getNumerojoueur() {
		return numerojoueur;
	}

	public String getName() {
		if (name == "")
			name = "Player"+String.valueOf(getNumerojoueur());
		return name;
	}

	public void setName(String name) throws PersonnageException {
		if (name == null)
			throw new PersonnageException("Attention, vous n'avez pas entrer votre nom");
		this.name = name;
	}

	public int getVie() {
		if (vie != 10)
			vie = 10;
		return vie;
	}

	public void setVie(int vie) throws PersonnageException {
		if (getVie()!= 10)
			throw new PersonnageException("Attention, vous n'avez pas autant de vie qu'il ne le faut");
		this.vie = vie;
	}

	public String toString() {
		return "Vous vous appelez " + getName() + " et vous commencez avec " + getVie() + " de vie";
	}

	public static void main(String[] args) throws PersonnageException {
		Personnage un = new Personnage("Pierre", 5);
		System.out.println(un);
		Personnage deux = new Personnage("Marie", 18);
		System.out.println(deux);
	}
}
