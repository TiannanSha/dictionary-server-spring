/**
 * @auther Tiannan Sha 861696
 * This class is used for running the entire server side
 */

package server;

public class ServerRunner {

    public static void main(String args[]) {
        if (args.length!=2) {
            System.out.println("ERROR: argument format not correct");
            System.out.println("USAGE: java -jar DictionaryServer.jar <port> <dictionary-file>");
            return;
        }
        int port;
        try {
            port = Integer.parseInt(args[0]);
        } catch (Exception e) {
            System.out.println("ERROR: argument format not correct");
            System.out.println("USAGE: java -jar DictionaryServer.jar <port> <dictionary-file>");
            return;
        }

        Server server = new Server(port, 10, args[1]);
        Thread t1 = new Thread(server);
        t1.start();
    }
}
