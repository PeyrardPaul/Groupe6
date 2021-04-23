import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

public class Serveur {
	public static final int PORT = 6000;
	// contiendra tous les flux de sortie vers les clients
	private static ArrayList<Partie> nb_parties = new ArrayList<Partie>();;
	private static ArrayList<Socket> nb_clients = new ArrayList<Socket>();;

	public static void main(String argv[]) throws UnknownHostException, IOException {
		Serveur serveur = new Serveur();

		ServerSocket serverSocket = null;
		System.out.println("Initialisation de la carte ...");
		Map carte = new Map(true);

		try {
			System.out.println("Demarrage du serveur ...");
			serverSocket = new ServerSocket(PORT);

		} catch (IOException e) {
			System.err.println("Le serveur n'a pas pu démarrer");
			System.err.println(e);
			System.exit(1);
		}

		Partie tmp = new Partie(carte);
		while (true) {

			Socket socket;
			try {
				socket = serverSocket.accept();
				System.out.println("Un joueur s'est connecté");
				
				Serveurjeu newPlayer = new Serveurjeu(socket,carte, serveur);
				newPlayer.start();
				tmp.addPlayer(newPlayer);
				
				if (tmp.ready()) {
					tmp.start();
					
					System.out.println("NOUVELLE PARTIE:  \n");
					nb_parties.add(tmp);
					carte = new Map(true);
					tmp = new Partie(carte);
				}
			} catch (IOException e) {
				System.err.println("Une erreur est arrivée lorsqu'un joueur a tenté de se connecter... ");
				System.err.println(e);
			}
		}

	}

	public static ArrayList<Partie> getNb_parties() {
		return nb_parties;
	}

	public static void setNb_parties(ArrayList<Partie> nb_parties) {
		Serveur.nb_parties = nb_parties;
	}

}