package  socket.server;


import controller.controllerLocal.ChessGameControler;
import java.io.*;
import java.net.*;

public class Accepter_connexion implements Runnable {

    private final ServerSocket socketserver = null;
    private Socket lastSocket = null;

    public Thread receiver;
    private final ChessGameControler controller;

    public Accepter_connexion(ServerSocket serverSocket, ChessGameControler controller) {
        this.controller = controller;
    }

    @Override
    public void run() {

        try {
            while (true) {

                lastSocket = socketserver.accept();
                System.out.println("Un Client se connecte ");
                receiver = new Thread(new ChessGameServeur(lastSocket, controller));
                receiver.start();

            }
        } catch (IOException e) {

            System.err.println("Erreur serveur");
        }

    }
}
