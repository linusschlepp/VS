package a1;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {

//          String socketHost = args[0];
//            int socketPort = Integer.parseInt(args[1]);

        String socketHost = "localhost";
        int socketPort = 1234;

        try {

            // Build connection to server
            Socket socket = new Socket(socketHost, socketPort);

            // Gets message from server
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("Bitte geben Sie eine Nachricht ein:");


            // Initialize connection to server
            PrintWriter writer = new PrintWriter(socket.getOutputStream());

            // Send message to server
            writer.println(new Scanner(System.in).nextLine());
            writer.flush();

            // Print message received from server
            System.out.println("Antwort von Server" + reader.readLine());


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
