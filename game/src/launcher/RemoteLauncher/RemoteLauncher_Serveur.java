package launcher.RemoteLauncher;

import controller.controllerLocal.ChessGameControler;
import java.io.IOException;
import java.net.ServerSocket;
import socket.server.Accepter_connexion;
import model.observable.ChessGame;



/**
 * @author francoise.perrin Lance le serveur
 *
 */
public class RemoteLauncher_Serveur {

    private static final int PORT = 5000;

    public static void main(String[] args) {
        ServerSocket serverSocket;

        ChessGame chess = new ChessGame();
        ChessGameControler controller = new ChessGameControler(chess);

        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("Le serveur est à l'écoute du port " + PORT);
        } catch (IOException e) {
            System.err.println("Le port " + PORT + " est déjà utilisé !");
            return;
        }

        Thread connectionServer = new Thread(new Accepter_connexion(serverSocket, controller));
        connectionServer.start();

    }
}
