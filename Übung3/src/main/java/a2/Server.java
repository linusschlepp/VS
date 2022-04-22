package a2;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {


    private final static int port = 8000;
    private final ServerSocket serverSocket;


    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }


    public static void main(String[] args) throws IOException {

        Server server = new Server(new ServerSocket(port));


        ExecutorService executor = Executors.newSingleThreadExecutor();
        System.out.print("Webserver wartet auf Anfragen.. ");



        while(true) {
            Socket clientRequest = server.serverSocket.accept();
            SocketThread socketThread = new SocketThread(clientRequest);

            executor.execute(socketThread);

        }


    }

}