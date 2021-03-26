import java.io.*;
import java.net.*;

public class Serveur {
    public static final int PORT=6000;

    public Serveur() {
         ServerSocket serverSocket = null;
            try {
                serverSocket = new ServerSocket(PORT);
            } catch (IOException e) {
                System.err.println("Le serveur n'a pas pu démarrer");
                System.err.println(e);
                System.exit(1);
            }

            while (true) {
                Socket socket;
                try {
                    socket = serverSocket.accept();
                    Serveurjeu newPlayer = new Serveurjeu(socket);
                    newPlayer.start();
                } catch (IOException e) {
                    System.err.println("Une erreure est arrivée lorsqu'un joueur a tenté de se connecter... ");
                    System.err.println(e);
                }
            }
    }

    public static void main(String argv[]) throws UnknownHostException, IOException {
        new Serveur();
    }
}