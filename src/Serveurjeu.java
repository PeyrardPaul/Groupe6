import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

public class Serveurjeu extends Thread {
	private Socket socket;
	private PrintWriter out;
	private BufferedReader in;
	private Map carte;
	private char[][] map;
	private Thread t;
	private Personnage pers;
	private Serveur serveur;
	private int numJoueur = 0;
	private static ArrayList<Personnage> joueurs = new ArrayList<Personnage>();;
	private static char[] avatar = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};;
	char avatarRun = 'X';
	

	public Serveurjeu(Socket socket, Map map, Serveur serveur) {
		try {
			this.socket = socket;
			out = new PrintWriter(socket.getOutputStream());
			//out = new PrintStream(socket.getOutputStream()); Pas sû donc en commentaire
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.carte = map;
			numJoueur = serveur.addClient(out);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		char myavatar = 'X';
		int i = 0;
		while (i < avatar.length) {

			if (avatar[i] != 'X') {
				myavatar = avatar[i];
				this.avatar[i] = 'X';
				/*if (myavatar == 'D') {
					avatar[0] = 'A';
					avatar[1] = 'B';
					avatar[2] = 'C';
					avatar[3] = 'D';
					i = 0;
				}*/
				break;
			}i++;
		}
		
		Personnage personne = new Personnage(myavatar);
		this.joueurs.add(personne);
		pers = personne;
		carte.addPersonnage(personne.getAvatar());
		this.out.println(myavatar);
		carte.affichage();
		avatarRun = myavatar;
	}

	public void depla() {
		String tmp = "";
		try {
			this.out.println(avatarRun);
			this.out.flush();
			tmp = in.readLine();
			carte.saisieClavier(pers, carte, avatarRun, tmp);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// step=0 le joueur vient de se connecter il faut taper jeu pour lancer la
		// partie,
		// step=1 ajout de joueurs dans la partie,
		// step=2 la partie commence,
		/*String tmp = "";
		
		while (true) {
			try {
					if (numJoueur == 2) {
						tmp = in.readLine();
						Iterator<Personnage> it = joueurs.iterator();
						while (it.hasNext()) {
							Personnage p = it.next();
							System.out.println(p.getAvatar());
							this.out.println(p.getAvatar());
							this.out.flush();
							tmp = in.readLine();
							System.out.println("Au joueur "+p.getAvatar()+" de jouer :");
							carte.saisieClavier(p, carte, p.getAvatar(), tmp);
							if (p.getPv() == 0) {
								System.err.println("Vous êtes MORT !!!");
							}
					}}
				
			}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		}*/
		
		/* Tant que vrai { 
			 * tant que ((le perso A ou que perso B n'a pas atteint la ligne d'arrivée) ou que (les deux sont morts)) {
			 * parcourir tabJoueurs de serveur {
			 * si dans tabjoueurs le numJoueur est 1 {
			 * faire methode map saisieclavier }
			 * si dans tabjoueurs le numJoueur est 2 {
			 * faire methode map saisie clavier } etc...
			 * }
			 * } false;*/
		/*int step = 0;
		while (true) {
				
				String tmp = "";
				boolean b = true;
				if (step == 0) {
					while (b == true) {
						System.out.println("Entrez 'jeu' pour commencer la partie");
						tmp = in.readLine();
						if (tmp.equals("jeu")) {
							this.out.println("jeu");
							step = 1;
							b = false;

						} else {
							this.out.println("Vous n'avez pas saisi 'jeu' ");
						}
						;
					}
				}
				
				if (step == 1) {
					//this.out.flush();
					//tmp = in.readLine();
					this.out.flush();
					char myavatar = 'X';
					for (int i = 0; i < avatar.length; i++) {

						if (avatar[i] != 'X') {
							myavatar = avatar[i];
							this.avatar[i] = 'X';
							break;
						}
						
					}

					Personnage personne = new Personnage(myavatar);
					this.joueurs.add(personne);
					carte.addPersonnage(personne.getAvatar());
					this.out.println(personne.getAvatar());
					step = 2;
					//this.out.println(carte.getAffichage());
					carte.affichage();
					this.out.flush();
				}
				if (step == 2) {
					// la partie a commencé, c'est ici qu'on va coder les action des joueurs durant
					// le jeu;
					tmp = in.readLine();
					carte.saisieClavier(personne, carte, 'A', tmp); en sachant que pour chaque numJoueur il y a un client, pour chaque client il y a un char et pour chaque client il y a un personnage a 
					appelé différent 
					
					
					 Tant que vrai { 
					 * tant que ((le perso A ou que perso B n'a pas atteint la ligne d'arrivée) ou que (les deux sont morts)) {
					 * parcourir tabJoueurs de serveur {
					 * si dans tabjoueurs le numJoueur est 1 {
					 * faire methode map saisieclavier }
					 * si dans tabjoueurs le numJoueur est 2 {
					 * faire methode map saisie clavier } etc...
					 * }
					 * } false;
					 
				}
				

				
				 * //on termine le jeu if(tmp.equals("quitter")) { this.socket.close(); } if
				 * (tmp.equals("jeu")) { //Personnage perso = new Personnage(); //Map carte =
				 * new Map(); //carte.jeu(perso, carte);
				 * 
				 * } else { out.println("Vous n'avez pas lancé la partie"); }
				 
				*/
	
	}
	
	public Personnage persServeur() {
		return pers;
	}

	public void mortPerso() {
		//map = carte.getMap(carte.getLigne(), carte.getColonne());
		map = new char[carte.getLigne()][carte.getColonne()];
		for (int i = 0; i < carte.getLigne(); i++) {
			for (int j = 0; j < carte.getColonne(); j++) {
				if (map[i][j] == lettrePers()) { // PROBLEME: la boucle ne trouve pas le perso donc ne peut pas rentrer dans le if
					System.out.println("le perso: -- "+lettrePers());
					System.out.println("l'emplacement: -- "+map[i][j]);
					map[i][j] = 'X';
				}
			}
		}
	}
	
	public char lettrePers() {
		return avatarRun;
	}
}	

