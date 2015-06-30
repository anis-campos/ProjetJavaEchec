package socket.server;

import socket.Common.Emission;
import controller.ChessGameControlers;
import java.io.*;
import java.net.*;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import socket.Common.Reception;

public class ChessGameServeur implements Runnable {

    private Socket lastSocket = null;
    private ObjectInputStream in = null;
    private ObjectOutputStream out = null;
    private Thread t3, t4;
    private final ChessGameControlers controller;
    private final InetAddress ClientIP;
    private final Map map;

    ChessGameServeur(Socket lastSocket, ChessGameControlers controller, Map map) {
        this.lastSocket = lastSocket;
        this.controller = controller;
        this.ClientIP = lastSocket.getInetAddress();
        this.map = map;
    }

    @Override
    public void run() {

        try {
            in = new ObjectInputStream(lastSocket.getInputStream());
            out = new ObjectOutputStream(lastSocket.getOutputStream());

            Object ID = in.readObject();

            System.out.println(ID);

            map.put((UUID) ID, lastSocket);

            t3 = new Thread(new Reception(in, controller));
            t3.start();
            t4 = new Thread(new Emission(out, controller));
            t4.start();

        } catch (IOException e) {
            System.err.println("Connection closed with the client that has IP Address: " + ClientIP);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ChessGameServeur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
