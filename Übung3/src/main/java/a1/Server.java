package a1;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {




    public static void main(String[] args) {

     //   int serverPort = Integer.parseInt(args[0]);

        int serverPort = 1234;

        try(ServerSocket serverSocket = new ServerSocket(serverPort)) {

                try {

                    System.out.println("Warte auf Verbindung...");

                    // Create connection to Client
                    Socket s = serverSocket.accept();

                    // Get message from Client
                    BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));

                    // Send message to Client
                    PrintWriter writer = new PrintWriter(s.getOutputStream());



                    String messageFromClient = reader.readLine();
                    System.out.println("Neue Verbindung von: " + s.getInetAddress() + "\n" + messageFromClient);

                    writer.println("*" +messageFromClient +"*");
                    writer.flush();

                    s.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }

            } catch(IOException e){
                e.printStackTrace();
            }


    }
}
