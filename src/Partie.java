import java.util.ArrayList;
import java.util.Iterator;

public class Partie {
	private Map map;
	private Serveur serv;
	private int i = 0;
	private char[][] joueur;
	private ArrayList<Serveurjeu> nb_joueurs = new ArrayList<Serveurjeu>();;
	
	public Partie (Map carte) {
		map = carte;
		if (serv.getNb_parties().size() != 0) {
			System.out.println("Nouvelle carte :");
			map = new Map(true);
		}
		map.affichage();
		map.casesPieges();
	}
	
	public void addPlayer(Serveurjeu joueur) {
		nb_joueurs.add(joueur);
	}
	
	public boolean ready() {
		if (nb_joueurs.size() == 4) {
			return true;
		}else {
			return false;
		}
	}
	
	public void start() {
		boolean b = true;
		char letter = 0;
		while (b) {
			Iterator <Serveurjeu> it = nb_joueurs.iterator();
			while (it.hasNext()) {
				Serveurjeu pp = it.next();
				System.out.println("Au tour de '"+pp.lettrePers()+"' de jouer");
				pp.depla();
				if (pp.persServeur().getPv() == 0) {
					System.out.println("Le joueur "+pp.lettrePers()+" est MORT !!!");
					//nb_joueurs.remove(pp);
					//pp.mortPerso();
				}
				if (pp.finJeu(map) == pp.lettrePers()) {
					b= false;
					letter = pp.lettrePers();
				}
			}
			if (nb_joueurs.size() == 0) {
				System.out.println("Tout le monde est mort...");
				b = false;
			}
		}
		System.out.println("Le joueur "+letter+" a trouvé la sortie du Donjon, "+letter+" a donc gagné !\n FIN DE LA PARTIE \n");
		
	}
	
	public boolean finClient(boolean b) {
		return b;
	}
}
