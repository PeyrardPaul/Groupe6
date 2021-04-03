import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    private PrintStream out = null;
    private BufferedReader in =null;
    private Socket socket;
    private Map carte;
    //private char moi;
    public Client() {
        try {
            socket = new Socket("127.0.0.1", 6000);
            out= new PrintStream( socket.getOutputStream() );
            in= new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loop() {
        Scanner sc = new Scanner(System.in);
        String tmp="";
        String line="";
        int step=0;
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
        			/*while(true) {
        				tmp=in.readLine();	
            			System.out.println(tmp);
            			if(tmp=="-1") {
            				break;
            			}
            			
        			}*/
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
			}
        	
            
           
        }
    }



    public static void main(String argv[]) {
        Client c =new Client();
        c.loop();
    }
}