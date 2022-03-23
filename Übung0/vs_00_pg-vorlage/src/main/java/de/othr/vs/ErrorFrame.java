package de.othr.vs;

import javax.swing.*;
import java.awt.*;

/**
 * Gets displayed if user enters wrong input data
 */
public class ErrorFrame {

    static void display(){

        // Basic set up of Frame
        JFrame frame = new JFrame("Error occurred");
        frame.setSize(300, 300);
        JPanel panel = new JPanel();
        panel.setLayout(null);

        // Set up errorFont, which is displayed in label
        Font errorFont  = new Font(Font.SANS_SERIF,  Font.BOLD, 10);
        JLabel errorLabel = new JLabel("Wrong input Data");
        errorLabel.setBounds(100,100,120,25);
        errorLabel.setForeground(Color.RED);
        errorLabel.setFont(errorFont);
        panel.add(errorLabel);

        // Set up Button
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
