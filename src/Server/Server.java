import java.io.*;
        import java.net.ServerSocket;
        import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {

        //https://stackoverflow.com/questions/443499/convert-json-to-map

        System.out.println("server: waiting for a connection");
        ServerSocket serverSocket = new ServerSocket(9000);
        Socket socket = serverSocket.accept();

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


    }



}

