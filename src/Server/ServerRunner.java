package Server;

import java.io.IOException;

public class ServerRunner {

    public static void main(String args[]) {
        Server server = new Server(9000, 10);
        Thread t1 = new Thread(server);
        t1.start();
    }
}
