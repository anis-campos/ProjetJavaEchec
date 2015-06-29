package socket.server;

import controller.controllerLocal.ChessGameControler;
import java.io.*;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Emission implements Runnable, Observer {

    private final ObjectOutputStream out;

    private final BlockingQueue<String> queue;

    Emission(ObjectOutputStream out, ChessGameControler controller) {
        this.out = out;
        this.queue = new ArrayBlockingQueue<>(10);
        controller.addObserver((Observer)this);
    }

    @Override
    public void run() {

        while (true) {
            try {
                String message = queue.take();
                System.out.println(message);
            } catch (InterruptedException ex) {
                Logger.getLogger(Emission.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        try {
            queue.put("Nouveau Message");
            out.writeObject(arg);
            
        } catch (InterruptedException | IOException ex) {
            Logger.getLogger(Emission.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
