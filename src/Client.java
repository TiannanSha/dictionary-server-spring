import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws IOException, InterruptedException {

        // create a socket that connects to the sever app and set up input and output
        // streams for that socket
        Socket socket = new Socket(InetAddress.getLocalHost(), 9000);
        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        DataInputStream dataInputStream = new DataInputStream(inputStream);

        while (true) {
            // write user input out thru socket
            String userInput = "I'm Client. Hello Sever";
            dataOutputStream.writeUTF(userInput);
            try {
                // listen to server's message
                String serverMsg = "";
                while (serverMsg=="") {
                    serverMsg = dataInputStream.readUTF();
                }
                // has received a server message, now process it
                System.out.println(serverMsg);

            } catch (Exception e) {
                System.out.println("Client error readUTF()");
            }
        }
    }
}