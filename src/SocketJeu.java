import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;

public class SocketJeu extends Thread{
    private Socket socket;
    private PrintStream out;
    private BufferedReader in;

    public SocketJeu(Socket socket) {
        try {
            this.socket=socket;
            out = new PrintStream( socket.getOutputStream() );
            in = new BufferedReader( new InputStreamReader( socket.getInputStream() ) );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void run() {
        int compteur = 5;
        try {
            while(compteur-->0) {
                String tmp = in.readLine();
                if (tmp.equals("heure")) {
                    out.println(System.nanoTime());
                }else {
                    out.println("bug");
                }
            }
            this.socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}