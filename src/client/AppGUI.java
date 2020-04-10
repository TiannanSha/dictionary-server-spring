package client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class AppGUI {
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton removeButton;
    private JButton addButton;
    private JButton searchButton;
    private JPanel panel1;
    private Client client;

    public AppGUI() {
        try {
            client = new Client(9000, InetAddress.getLocalHost());
        } catch (UnknownHostException e) { e.printStackTrace(); }

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String word = textField1.getText();
                String meaning = textField2.getText();
                String msg = "add*" + word + "*" + meaning;
                try {
                    client.sendMsg(msg);
                    String response = client.listenForResponse();
                    textField3.setText(response);

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
        frame.setContentPane(new AppGUI().panel1);
        frame.setSize(800, 480); // make all components reach the size set in the form
        frame.setLocationRelativeTo(null); // make the frame appear at the centre of the screen
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


}


