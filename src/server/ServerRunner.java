package server;

public class ServerRunner {

    public static void main(String args[]) {
        Server server = new Server(9000, 10, args[0]);
        Thread t1 = new Thread(server);
        t1.start();
    }
}
