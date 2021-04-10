import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Vector;

public class Serveur {
	public static final int PORT = 6000;
	private Vector tabJoueurs = new Vector();
	// contiendra tous les flux de sortie vers les clients
	private static ArrayList<Partie> nb_parties = new ArrayList<Partie>();;
	private int nbJoueurs = 0;
	private static int i = 0;

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

	synchronized public int addClient(PrintWriter out) {
		nbJoueurs++; // un client en plus ! ouaaaih
		tabJoueurs.addElement(out); // on ajoute le nouveau flux de sortie au tableau
		return tabJoueurs.size(); // on retourne le numéro du client ajouté (size-1)
	}

	synchronized public int numJoueur() {
		return tabJoueurs.size();
	}

	synchronized public void sendAll(String message, String sLast) {
		PrintWriter out; // déclaration d'une variable permettant l'envoi de texte vers le client
		for (int i = 0; i < tabJoueurs.size(); i++) // parcours de la table des connectés
		{
			out = (PrintWriter) tabJoueurs.elementAt(i); // extraction de l'élément courant (type PrintWriter)
			if (out != null) // sécurité, l'élément ne doit pas être vide
			{
				// écriture du texte passé en paramètre (et concaténation d'une string de fin de
				// chaine si besoin)
				out.print(message + sLast);
				out.flush(); // envoi dans le flux de sortie
			}
		}
	}

}