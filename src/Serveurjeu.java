import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Serveurjeu extends Thread {
	private Socket socket;
	private PrintStream out;
	private BufferedReader in;
	private Map carte;
	private static ArrayList<Personnage> joueurs = new ArrayList<Personnage>();;
	private static char[] avatar = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'H' };;
	//char myavatar = 'X';
	//Personnage personne = new Personnage(myavatar);
	

	public Serveurjeu(Socket socket, Map map) {
		try {
			this.socket = socket;
			out = new PrintStream(socket.getOutputStream());
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.carte = map;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		// step=0 le joueur vient de se connecter il faut taper jeu pour lancer la
		// partie,
		// step=1 ajout de joueurs dans la partie,
		// step=2 la partie commence,
		int step = 0;
		while (true) {

			try {

				String tmp = "";
				boolean b = true;
				if (step == 0) {
					while (b == true) {
						System.out.println("Entrez 'jeu' pour commencer la partie");
						tmp = in.readLine();
						if (tmp.equals("jeu")) {
							this.out.println("jeu");
							step = 1;
							System.out.println("La partie commence :");
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
				}

				/*
				 * //on termine le jeu if(tmp.equals("quitter")) { this.socket.close(); } if
				 * (tmp.equals("jeu")) { //Personnage perso = new Personnage(); //Map carte =
				 * new Map(); //carte.jeu(perso, carte);
				 * 
				 * } else { out.println("Vous n'avez pas lancé la partie"); }
				 */

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}