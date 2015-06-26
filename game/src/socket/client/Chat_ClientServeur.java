package socket.client;
import java.io.*;
import java.net.*;


public class Chat_ClientServeur implements Runnable {

	private Socket socket;
	private OutputStream localOut = null;
	private InputStream localIn = null;
	private Thread t3, t4;

	public Chat_ClientServeur(Socket s, InputStream in, OutputStream out){
		socket = s;
                this.localIn = in;
                this.localOut = out;
                
	}

        @Override
	public void run() {
		try {
			ObjectOutputStream socketOut = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream socketIn = new ObjectInputStream(socket.getInputStream());


			Thread t4 = new Thread(new Emission(socketOut,localIn));
			t4.start();
			Thread t3 = new Thread(new Reception(socketIn,localOut));
			t3.start();



		} catch (IOException e) {
			System.err.println("Le serveur distant s'est d�connect� !");
		}
	}

}