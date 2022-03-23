package de.othr.vs;

import javax.swing.*;
import java.awt.*;

public class ErrorFrame {

    static void display(){

        JFrame frame = new JFrame("Error occurred");
        frame.setSize(300, 300);
        JPanel panel = new JPanel();
        panel.setLayout(null);


        Font errorFont  = new Font(Font.SANS_SERIF,  Font.BOLD, 10);
        JLabel errorLabel = new JLabel("Wrong input Data");
        errorLabel.setBounds(100,100,120,25);
        errorLabel.setFont(errorFont);
        panel.add(errorLabel);

        JButton againButton = new JButton("Try again");
        againButton.addActionListener(e -> {
            SwingFrame.display();
            frame.dispose();
        });
        againButton.setBounds(100, 120, 130,25);
        panel.add(againButton);


        frame.add(panel);
        frame.setVisible(true);


    }

}
