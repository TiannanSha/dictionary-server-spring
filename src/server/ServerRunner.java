/**
 * @auther Tiannan Sha 861696
 * This class is used for running the entire server side
 */

package server;

public class ServerRunner {

    public static void main(String args[]) {
        Server server = new Server(9000, 10, args[0]);
        Thread t1 = new Thread(server);
        t1.start();
    }
}
