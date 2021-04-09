import java.util.ArrayList;
import java.util.Iterator;

public class Partie {
	private Map map;
	private ArrayList<Serveurjeu> nb_joueurs = new ArrayList<Serveurjeu>();;
	
	public Partie (Map carte) {
		map = carte;
		map = new Map(true);
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
		while (true) {
			Iterator <Serveurjeu> it = nb_joueurs.iterator();
			while (it.hasNext()) {
				Serveurjeu pp = it.next();
				System.out.println("Au tour de '"+pp.lettrePers()+"' de jouer");
				pp.depla();
				if (pp.persServeur().getPv() == 0) {
					nb_joueurs.remove(pp);
				}
			}
		}
	}
}
