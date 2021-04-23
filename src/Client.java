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
    static String tmp="";
    static String tmp2 = "";
    Scanner sc = new Scanner(System.in);
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
    	boolean b = true;
    	while (b) {
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
    	}         
    }


    public static void main(String argv[]) {
        Client c =new Client();
    		c.loop();
    }
}