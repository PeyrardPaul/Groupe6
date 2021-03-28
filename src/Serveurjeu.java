import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;

public class Serveurjeu extends Thread {
	private Socket socket;
	private PrintStream out;
	private BufferedReader in;
	private Map carte;
	private static ArrayList<Personnage> jouers=new ArrayList<Personnage>();;
	private static char[] avatar=new char[] {'A','B','C'};;

	
	

	public Serveurjeu(Socket socket,Map map) {
		try {
			this.socket = socket;
			out = new PrintStream(socket.getOutputStream());
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));		
			this.carte=map;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		//step=0 le joeur viens de conntecter il faut taper jeu pour lancer la parite,
		//step=1 ajoute de joeur dans la partie,
		//step=2 la partie commence,
		int step=0;
		while(true) {
			
			
			try {
				
				
				String tmp="";
				if(step==0) {
					tmp=in.readLine();
					if(tmp.equals("jeu")) {
						this.out.println("jeu");
						step=1;
						
					}else{
						this.out.println("Qu'est que vous faite là");
					};
				}
				if(step==1) {
					this.out.println("Entre votre nom");
					this.out.flush();
					tmp = in.readLine();
					this.out.flush();
					char myavatar='X';
				for (int i = 0; i < avatar.length; i++) {
					
						if(avatar[i]!='X') {
							myavatar=avatar[i];
							this.avatar[i]='X';
							System.out.println(this.avatar[i]);
							break;
						}
					}
					
					Personnage personne=new Personnage(myavatar);
					this.jouers.add(personne);
					carte.addPersonnage(personne.getAvatar());
					this.out.println(personne.getAvatar());
					step=2;
					this.out.println(carte.getAffichage());
					this.out.flush();
					
				}
				if(step==2) {
					//la partie est commencer c'est ici qu'on vas coder les action des joeur durant le jeux;
				    tmp = in.readLine();
				 
				}
				
				
				
			

			
				
				
				
				
				
				
				
				
				
			/*	//on termine le jeux
				if(tmp.equals("quitter")) {
					this.socket.close();
				}
				if (tmp.equals("jeu")) {
					//Personnage perso = new Personnage();
					//Map carte = new Map();
					//carte.jeu(perso, carte);
					
				} else {
					out.println("Vous n'avez pas lancé la partie");
				}*/
			

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

}