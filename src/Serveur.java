import java.io.*;
import java.net.*;

public class Serveur {
	public static final int PORT = 6000;

	public Serveur() {
		ServerSocket serverSocket = null;
		System.out.println("Initialisation de la carte ...");
		Map carte = new Map(true);

		try {
			System.out.println("Demarrage de serveur ...");
			serverSocket = new ServerSocket(PORT);

		} catch (IOException e) {
			System.err.println("Le serveur n'a pas pu démarrer");
			System.err.println(e);
			System.exit(1);
		}

		while (true) {

			Socket socket;
			try {
				System.out.println("En attente du joueur");
				socket = serverSocket.accept();
				System.out.println("Un joueur est connecté");
				Serveurjeu newPlayer = new Serveurjeu(socket, carte);
				newPlayer.start();
			} catch (IOException e) {
				System.err.println("Une erreur est arrivée lorsqu'un joueur a tenté de se connecter... ");
				System.err.println(e);
			}
		}
	}

	public static void main(String argv[]) throws UnknownHostException, IOException {
		new Serveur();
	}

}