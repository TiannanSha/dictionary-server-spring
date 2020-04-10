package server;

import java.io.*;
        import java.net.ServerSocket;
        import java.net.Socket;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server implements Runnable {

    //private ServerSocket serverSocket;
    private int port;
    private int threadPoolSize;
    private ExecutorService threadPool;
    private Dict dict;

    public Server(int port, int threadPoolSize, String dictFile) {
        this.port = port;
        this.threadPoolSize = threadPoolSize;
        threadPool = Executors.newFixedThreadPool(threadPoolSize);
        this.dict = new Dict(dictFile);
    }

    // this server has one thread waiting for connections, when there is a connection,
    // submit a clientHandler to the threadPool to use a thread to handle that client
    @Override
    public void run() {
        ServerSocket serverSocket = null;
        try { serverSocket = new ServerSocket(port); } catch (IOException e) { e.printStackTrace(); }
        System.out.println("Waiting for connections...");
        while (true) {
            Socket clientSocket = null;
            try { clientSocket = serverSocket.accept(); } catch (IOException e) { e.printStackTrace(); }
            // run handleOneClient using a thread in the threadPool
            threadPool.submit(new ClientHandler(clientSocket));
        }
    }

    private String handleClientMsg(String msg) {
        String[] clientMsgWords = msg.split("\\*");
        //System.out.println(Arrays.toString(clientMsgWords));
        String command = clientMsgWords[0];
        if (command.equals("add")) {
            if (clientMsgWords.length!=3) {
                return "ERROR: client add message should be of form [add word meaning]";
            }
            String word = clientMsgWords[1];
            String meaning = clientMsgWords[2];
            return dict.addWord(word, meaning);
        } else if (command.equals("search")) {
            if (clientMsgWords.length!=2) {
                return "ERROR: client search message should be of form [search word]";
            }
            String word = clientMsgWords[1];
            return dict.search(word);
        } else if (command.equals("remove")) {
            if (clientMsgWords.length!=2) {
                return "ERROR: client remove message should be of form [remove word]";
            }
            String word = clientMsgWords[1];
            return dict.removeWord(word);
        } else {
            return "ERROR: unknown command received";
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
            while (true) {
                try {
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
                    System.out.println("clientMsg:" + clientMsg);
                    String response = handleClientMsg(clientMsg);

                    // send message to the client
                    //String meaning = "HI there client, I like it when you say:" + clientMsg;
                    dataOutputStream.writeUTF(response);
                } catch(IOException e) {
                    // one of the clients has disconnected, do nothing
                }

            }

        }
    }


}

