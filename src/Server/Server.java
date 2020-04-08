package Server;

import java.io.*;
        import java.net.ServerSocket;
        import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server implements Runnable {

    //private ServerSocket serverSocket;
    private int port;
    private int threadPoolSize;
    private ExecutorService threadPool;

    public Server(int port, int threadPoolSize) {
        this.port = port;
        this.threadPoolSize = threadPoolSize;
        threadPool = Executors.newFixedThreadPool(threadPoolSize);
        //serverSocket = new ServerSocket(port);
        System.out.println("waiting for connections");
    }

    // this server has one thread waiting for connections, when there is a connection,
    // submit a clientHandler to the threadPool to use a thread to handle that client
    @Override
    public void run() {
        ServerSocket serverSocket = null;
        try { serverSocket = new ServerSocket(port); } catch (IOException e) { e.printStackTrace(); }
        System.out.println("Waiting for clients to connect...");
        while (true) {
            Socket clientSocket = null;
            try { clientSocket = serverSocket.accept(); } catch (IOException e) { e.printStackTrace(); }
            // run handleOneClient using a thread in the threadPool
            threadPool.submit(new ClientHandler(clientSocket));
        }
    }


    //https://stackoverflow.com/questions/443499/convert-json-to-map
    private class ClientHandler implements Runnable {
        Socket socket;
        ClientHandler(Socket socket) {
            this.socket = socket;  // set socket for that client
        }

        @Override
        public void run() {
            try {
                while (true) {
                    // set up input output data streams
                    OutputStream outputStream = socket.getOutputStream();
                    DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
                    InputStream socketIn = socket.getInputStream();
                    DataInputStream dataInputStream = new DataInputStream(socketIn);

                    // listen to client's message
                    String clientMsg = "";
                    while (clientMsg == "") {
                        clientMsg = dataInputStream.readUTF();
                    }
                    // have read a client message, now process it
                    System.out.println(clientMsg);

                    // send message to the client
                    String meaning = "HI there client, I like it when you say:" + clientMsg;
                    dataOutputStream.writeUTF(meaning);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}

