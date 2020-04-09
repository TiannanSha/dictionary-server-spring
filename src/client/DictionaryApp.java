package client;

import javax.swing.*;
import java.awt.*;

public class DictionaryApp {
    private JPanel panel1;
    private JButton searchAWordInButton;
    private JButton removeAWordFromButton;
    private JButton addAWordToButton;
    private JTextArea textArea1;
    private JTextArea textArea2;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Dictionary App");
        // set this frame's JPanel to be the JPanel in the form
        frame.setContentPane(new DictionaryApp().panel1);
        frame.pack(); // make all components reach the size set in the form

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
