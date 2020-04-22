/**
 * @auther Tiannan Sha 861696
 * This class implements the client side's core logics
 */

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
}