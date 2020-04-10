package client;

import jdk.internal.util.xml.impl.Input;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

    private int port = 9000;
    private InetAddress serverAddress;
    private Socket socket;
    private InputStream inputStream;
    private DataInputStream dataInputStream;
    private OutputStream outputStream;
    private DataOutputStream dataOutputStream;

    public Client(int port, InetAddress serverAddress) {
        this.port = port;
        this.serverAddress = serverAddress;
        try {
            // set up sockets and input, output streams
            socket = new Socket(serverAddress, port);
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
            dataOutputStream = new DataOutputStream(outputStream);
            dataInputStream = new DataInputStream(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // when a button is clicked, client sends a message to the server and listens to server's response
    public void sendMsg(String msg) throws IOException {
        dataOutputStream.writeUTF(msg);
        //return "";
    }

    // block and wait until a response from server has been read
    public String listenForResponse() throws IOException {
        String response = "";
        while (response.equals("")) {
            response = dataInputStream.readUTF();
        }
        return response;
    }

    public static void main(String[] args) throws IOException, InterruptedException {

        // create a socket that connects to the sever app and set up input and output
        // streams for that socket
        Socket socket = new Socket(InetAddress.getLocalHost(), 9000);
        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // write user input out thru socket
            String userInput = scanner.nextLine(); // nextline() blocks and wait for client input
            dataOutputStream.writeUTF(userInput);
            try {
                // listen to server's message
                String serverMsg = "";
                while (serverMsg == "") {
                    serverMsg = dataInputStream.readUTF();
                }
                // has received a server message, now process it
                System.out.println(serverMsg);

            } catch (Exception e) {
                System.out.println("Client.Client error readUTF()");
            }
        }
    }
}

// java gui tutorial
// https://www.youtube.com/watch?v=WRwPVZ4jmNY