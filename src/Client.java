import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    private PrintStream out = null;
    private static BufferedReader in =null;
    private Socket socket;
    private Map carte;
    private int ident;
    static String tmp="";
    static String tmp2 = "";
    Scanner sc = new Scanner(System.in);
    private static int id = 1;//exo 4
    //private char mi;
    public Client() {
        try {
        	ident = id;
            socket = new Socket("127.0.0."+String.valueOf(ident), 6000);
            out= new PrintStream( socket.getOutputStream() );
            in= new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        id++;
        // ATTENTION: Je veux changer d' ID à chaque fois que l'on crée un client pour pas que tout les clients aient les mêmes ID
        //Donc à chaque fois j'ajoute au dernier chiffre de l'id +1 (127.0.etc..), mais ça ne marche pas et le petit id reste égal à 1
    }
    
    public void loop() {
    
    		try {
    			tmp = in.readLine();
    			System.out.println("Au joueur '"+tmp+"' de jouer :\n ------------");
    			System.out.println("Dans quelle direction voulez vous aller?");
    			System.out.println("z = vers le haut");
    			System.out.println("s = vers le bas");
    			System.out.println("q = vers la gauche");
    			System.out.println("d = vers la droite");
    			System.out.println("e = prendre une potion");
    			String a = sc.next();
    			this.out.println(a);
    			
    		}catch(IOException e) {
    			e.printStackTrace();
    		}
        /*int step=0;
        while(true) {     
        	try {
        		if(step==0) {
        			line = sc.next();
        			this.out.println(line);
        			tmp=in.readLine();	
        			if(tmp.equals("jeu")) {
        			step=1;
        			}else {
        				System.out.println(tmp);
        			}
        		
        		}
        		
        		if(step==1) {
        			tmp=in.readLine();	
        			//System.out.println(tmp);
        			//line = sc.next();
        			//this.out.println(line);
        			//tmp=in.readLine();	
        			System.out.println("Bienvenue, vous êtes '"+tmp+"' dans le jeu");
        			while(true) {
        				tmp=in.readLine();	
            			System.out.println(tmp);
            			if(tmp=="-1") {
            				break;
            			}
            			
        			}
        			step=2;		
        		}
			
				if (step ==2) {
					Personnage perso = new Personnage (tmp.charAt(0));
					System.out.println("Dans quelle direction voulez vous aller?");
					System.out.println("z = vers le haut");
					System.out.println("s = vers le bas");
					System.out.println("q = vers la gauche");
					System.out.println("d = vers la droite");
					System.out.println("e = prendre une potion");
					String a = sc.next();
					this.out.println(a);
					step = 3;
				}
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}*/
        	
            
           
        }
  



    public static void main(String argv[]) {
        Client c =new Client();
        boolean b = true;
    	while (b) {
    		c.loop();
        }
        /*try {     Pour la fois où chaque pers aura jouer et que tout les joueurs devront rejouer
        	tmp = in.readLine();
        	if (tmp.equals("Prochain tour")) {
        		c.loop();
        	}
        }catch (IOException e) {
    	e.printStackTrace();
        }*/
    }
}