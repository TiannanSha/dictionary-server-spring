package client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class DictionaryApp {
    private JPanel panel1;
    private JButton searchAWordInButton;
    private JButton removeAWordFromButton;
    private JButton addAWordToButton;
    private JTextArea textArea1;
    private JTextArea textArea2;
    private JTextPane resultTextPane;
    private Client client;

    public DictionaryApp() {
        try {
            client = new Client(9000, InetAddress.getLocalHost());
        } catch (UnknownHostException e) { e.printStackTrace(); }

        addAWordToButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String word = textArea1.getText();
                String meaning = textArea2.getText();
                String msg = "add*" + word + "*" + meaning;
                try {
                    client.sendMsg(msg);
                    String response = client.listenForResponse();
                    resultTextPane.setText(response);

                } catch (IOException ex) {
                    // server internal error

                }
            }
        });
    }

    public static void main(String[] args) {
        //TODO change client to use provided args
        //client = new Client(port, address);

        JFrame frame = new JFrame("Dictionary App");
        // set this frame's JPanel to be the JPanel in the form
        frame.setContentPane(new DictionaryApp().panel1);
        frame.pack(); // make all components reach the size set in the form
        frame.setLocationRelativeTo(null); // make the frame appear at the centre of the screen
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }

}
