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
		boolean a = true;
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
					it.remove();
				}
				if (pp.finJeu(map) == pp.lettrePers()) {
					b= false;
					a = false;
					letter = pp.lettrePers();
				}
			}
			if (nb_joueurs.size() == 1) {
				Iterator <Serveurjeu> i = nb_joueurs.iterator();
				while (i.hasNext()) {
					Serveurjeu p = i.next();
					System.out.println("Il ne reste plus qu'un survivant ...\nLe joueur "+p.lettrePers()+" a donc gagné !\nFIN DE LA PARTIE \n");
					b = false; 
				}
			}
			
			if (nb_joueurs.size() == 0) {
				System.out.println("Tout le monde est mort...\nFIN DE LA PARTIE");
				b = false;
			}
		}
		if (a == false) {
			System.out.println("Le joueur "+letter+" a trouvé la sortie du Donjon, "+letter+" a donc gagné !\nFIN DE LA PARTIE \n");
		}
	}

	public boolean finClient(boolean b) {
		return b;
	}
}
