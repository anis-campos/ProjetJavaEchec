package socket.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Reception implements Runnable {

    private final ObjectInputStream socketIn;
    private final OutputStream localOut;

    public Reception(ObjectInputStream socketIn, OutputStream localOut) {
        this.socketIn = socketIn;
        this.localOut = localOut;
    }

    @Override
    public void run() {

        while (true) {
            try {

                ObjectOutputStream out = new ObjectOutputStream(localOut);
                out.writeObject(socketIn.readObject());

            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(Reception.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
