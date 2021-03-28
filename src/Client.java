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
    private char moi;
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
        			System.out.println(tmp);
        			line = sc.next();
        			this.out.println(line);
        			tmp=in.readLine();	
        			System.out.println("Bienvenue, vous êtez "+tmp+" dans le jeu");
        			while(true) {
        				tmp=in.readLine();	
            			System.out.println(tmp);
            			if(tmp=="-1") {
            				break;
            			}
            			
        			}
        			step=1;
        			
        			
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