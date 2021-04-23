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
	private Personnage pers;
	private Serveur serveur;
	private static ArrayList<Personnage> joueurs = new ArrayList<Personnage>();;
	private static char[] avatar = new char[] { 'A', 'B', 'C', 'D'};;
	char avatarRun = 'X';
	

	public Serveurjeu(Socket socket, Map map, Serveur serveur) {
		try {
			this.socket = socket;
			out = new PrintWriter(socket.getOutputStream());
			//out = new PrintStream(socket.getOutputStream()); Pas sû donc en commentaire
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.carte = map;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		char myavatar = 'X';
		int i = 0;
		while (i < avatar.length) {

			if (avatar[i] != 'X') {
				myavatar = avatar[i];
				this.avatar[i] = 'X';
				if (myavatar == 'D') {
					avatar[0] = 'A';
					avatar[1] = 'B';
					avatar[2] = 'C';
					avatar[3] = 'D';
					i = -1;
				}
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
			carte.saisieClavierMulti(pers, carte, avatarRun, tmp);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Personnage persServeur() {
		return pers;
	}
	
	public char finJeu(Map carte) {
		return carte.getMap()[1][1];
	}
	public char lettrePers() {
		return avatarRun;
	}
}	

