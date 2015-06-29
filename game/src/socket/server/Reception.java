package socket.server;

import controller.controllerLocal.ChessGameControler;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Couleur;

public class Reception implements Runnable {

    private static Couleur JoueurEnCours = Couleur.BLANC;
    private final ObjectInputStream in;
    private final ChessGameControler controller;

    Reception(ObjectInputStream in, ChessGameControler controller) {
        this.in = in;
        this.controller = controller;
    }

    @Override
    public void run() {

        while (true) {

            try {
                Object readObject = in.readObject();
                System.out.println("Nouveau message recu : " + readObject.toString());

            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(Reception.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
