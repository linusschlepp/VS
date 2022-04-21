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
                    Socket s = serverSocket.accept();

                    InputStream in = s.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                    OutputStream out = s.getOutputStream();
                    PrintWriter writer = new PrintWriter(out);

                    String message = reader.readLine();
                    System.out.println("Neue Verbindung von: " + s.getInetAddress() + "\n" + message);

                    writer.println("*" +message +"*");
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
