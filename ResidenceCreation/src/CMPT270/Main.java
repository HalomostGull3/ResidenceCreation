package CMPT270;

import gui.CreateResidenceFrame;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        JPanel panel = new JPanel();
        JFrame frame = new JFrame();
        JTextField textField = new JTextField(10);


        panel.add(textField);

        frame.setSize(300, 300);
        frame.setLocation(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
