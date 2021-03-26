import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;

public class Serveurjeu extends Thread {
	private Socket socket;
	private PrintStream out;
	private BufferedReader in;

	public Serveurjeu(Socket socket) {
		try {
			this.socket = socket;
			out = new PrintStream(socket.getOutputStream());
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		try {
			String tmp = in.readLine();
			if (tmp.equals("jeu")) {
				Personnage perso = new Personnage();
				Map carte = new Map();
				carte.jeu(perso, carte);
			} else {
				out.println("Vous n'avez pas lancé la partie");
			}
			this.socket.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}