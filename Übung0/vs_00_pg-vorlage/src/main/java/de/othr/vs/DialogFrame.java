package de.othr.vs;

import javax.swing.*;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;

public class DialogFrame {

    static JTextArea chatArea = new JTextArea();
    static HashMap<JButton, StringBuilder> buttonStringBuilderHashMap = new HashMap<>();
    static AtomicReference<JButton> selectButton = new AtomicReference<>(new JButton());
    static JTextField inputField = new JTextField();

    static void display() {


        // Set up frame and general Layout
        JFrame frame = new JFrame("Chats");
        frame.setSize(700, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(null);

        // Initialized chatArea
        chatArea.setBounds(200, 10, 500, 200);
        panel.add(chatArea);

        // Initialized inputField
        inputField.setBounds(200, 230, 165, 25);
        panel.add(inputField);



        // Created and initialized buttons
        JButton buttonChat1 = new JButton("Linus");
        buttonChat1.setBounds(10, 10, 80, 25);
        buttonChat1.addActionListener(e -> {
            selectButton.set(buttonChat1);
            changeChatArea();
        });


        panel.add(buttonChat1);
        //default initialization of Button
        selectButton.set(buttonChat1);
        buttonStringBuilderHashMap.put(buttonChat1, new StringBuilder());
        JButton buttonChat2 = new JButton("Marcel");
        buttonChat2.setBounds(10, 50, 80, 25);
        buttonChat2.addActionListener(e -> {
            selectButton.set(buttonChat2);
            changeChatArea();
        });
        panel.add(buttonChat2);
        buttonStringBuilderHashMap.put(buttonChat2, new StringBuilder());
        JButton buttonChat3 = new JButton("Simon");
        buttonChat3.setBounds(10, 90, 80, 25);
        buttonChat3.addActionListener(e -> {
            selectButton.set(buttonChat3);
            changeChatArea();
        });
        panel.add(buttonChat3);
        buttonStringBuilderHashMap.put(buttonChat3, new StringBuilder());
        JButton buttonChat4 = new JButton("David");
        buttonChat4.setBounds(10, 130, 80, 25);
        buttonChat4.addActionListener(e -> {
            selectButton.set(buttonChat4);
            changeChatArea();
        });
        panel.add(buttonChat4);
        buttonStringBuilderHashMap.put(buttonChat4, new StringBuilder());
        JButton sendChat = new JButton("Send");
        sendChat.setBounds(380, 230, 80, 25);
        sendChat.addActionListener(e -> {
            buttonStringBuilderHashMap.get(selectButton.get()).append(inputField.getText()).append("\n\n");
            changeChatArea();

        });
        panel.add(sendChat);


        frame.add(panel);
        frame.setVisible(true);
    }

    // Changes chatArea of DialogFrame
    private static void changeChatArea() {
        chatArea.setText("");
        chatArea.setText(buttonStringBuilderHashMap.get(selectButton.get()).toString());
        inputField.setText("");
    }
}
