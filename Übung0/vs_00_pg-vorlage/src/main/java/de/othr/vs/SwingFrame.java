package de.othr.vs;

import javax.swing.*;
import java.awt.*;


public class SwingFrame {

    static void display() {

        // Set up general Layout
        JFrame frame = new JFrame("Anmelden");
        frame.setSize(350, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(null);
        frame.add(panel);

        // Set up Labels
        JLabel labelUsername = new JLabel("Username: ");
        labelUsername.setBounds(10, 20, 80, 25);
        panel.add(labelUsername);
        JLabel labelPassword = new JLabel("Passwort: ");
        labelPassword.setBounds(10, 50, 80, 25);
        panel.add(labelPassword);

        // Set up TextFields
        JTextField textFieldUsername = new JTextField(20);
        textFieldUsername.setBounds(100, 20, 165, 25);
        panel.add(textFieldUsername);
        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setBounds(100, 50, 165, 25);
        panel.add(passwordField);

        // Set up Buttons and button functionality
        JButton buttonLogin = new JButton("Login");
        buttonLogin.setBounds(10, 80, 80, 25);
        buttonLogin.addActionListener(e -> {
//            System.out.println(String.valueOf(passwordField.getPassword()));
//            System.out.println(textFieldUsername.getText());
            if (String.valueOf(passwordField.getPassword()).equals("1234") &&
                    textFieldUsername.getText().equals("Linus"))
                DialogFrame.display();
            else
                ErrorFrame.display();

        });
        panel.add(buttonLogin);
        JButton buttonExit = new JButton("Exit");
        buttonExit.setBounds(100, 80, 80, 25);
        buttonExit.addActionListener(e -> frame.dispose());
        panel.add(buttonExit);


        frame.setVisible(true);


    }

}
