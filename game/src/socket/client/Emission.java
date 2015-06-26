package socket.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Emission implements Runnable {

    private final ObjectOutputStream socketOut;
    private final InputStream localIn;

    public Emission(ObjectOutputStream socketOut, InputStream localIn) {
        this.socketOut = socketOut;
        this.localIn = localIn;
    }

    @Override
    public void run() {
        while (true) {
            try {
                ObjectInputStream in = new ObjectInputStream(localIn);
                socketOut.writeObject(in.readObject());
                socketOut.flush();
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(Emission.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
