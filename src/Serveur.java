import java.io.*;
import java.net.*;
import java.util.Vector;

public class Serveur {
	public static final int PORT = 6000;
	private Vector tabJoueurs = new Vector(); // contiendra tous les flux de sortie vers les clients
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
			System.err.println("Le serveur n'a pas pu d�marrer");
			System.err.println(e);
			System.exit(1);
		}

		while (i != 3) {

			Socket socket;
			try {
				System.out.println("En attente d'un joueur");
				socket = serverSocket.accept();
				System.out.println("Un joueur s'est connect�");
				Serveurjeu newPlayer = new Serveurjeu(socket, carte, serveur);
				newPlayer.start();
			} catch (IOException e) {
				System.err.println("Une erreur est arriv�e lorsqu'un joueur a tent� de se connecter... ");
				System.err.println(e);
			}
			i += 1;
			if (i == 3) {
				System.out.println("Le nombre maximum de joueur est atteint, la partie peut commencer !!!");
			}
		}

	}

	synchronized public int addClient(PrintWriter out) {
		nbJoueurs++; // un client en plus ! ouaaaih
		tabJoueurs.addElement(out); // on ajoute le nouveau flux de sortie au tableau
		return tabJoueurs.size(); // on retourne le num�ro du client ajout� (size-1)
	}

	synchronized public int numJoueur() {
		return tabJoueurs.size();
	}

	synchronized public void sendAll(String message, String sLast) {
		PrintWriter out; // d�claration d'une variable permettant l'envoi de texte vers le client
		for (int i = 0; i < tabJoueurs.size(); i++) // parcours de la table des connect�s
		{
			out = (PrintWriter) tabJoueurs.elementAt(i); // extraction de l'�l�ment courant (type PrintWriter)
			if (out != null) // s�curit�, l'�l�ment ne doit pas �tre vide
			{
				// �criture du texte pass� en param�tre (et concat�nation d'une string de fin de
				// chaine si besoin)
				out.print(message + sLast);
				out.flush(); // envoi dans le flux de sortie
			}
		}
	}

}