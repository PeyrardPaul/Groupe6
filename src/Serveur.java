import java.io.*;
import java.net.*;

public class Serveur {
    public static final int PORT=6000;

    public Serveur() {
         ServerSocket serverSocket = null;
         System.out.println("Initialisation du carte ...");
         Map carte=new Map(true);
       
            try {
            	System.out.println("Demarage de serveur ...");
                serverSocket = new ServerSocket(PORT);
            	
              
            } catch (IOException e) {
                System.err.println("Le serveur n'a pas pu démarrer");
                System.err.println(e);
                System.exit(1);
            }

            while (true) {
            	
                Socket socket;
                try {
                	System.out.println("En attente du joeur");
                    socket = serverSocket.accept();
                	System.out.println("Un joeur est connecté");
                    Serveurjeu newPlayer = new Serveurjeu(socket,carte);
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