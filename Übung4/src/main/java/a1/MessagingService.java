package a1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;



public class MessagingService {

    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(8080)) {


            MessageStore messageStore = new MessageStore();

                // System.out.println("a1.Client connected....  ");
                while(true) {
                    Socket socket = serverSocket.accept();
                    System.out.println("Client connected....  ");

                    ClientRequest clientRequest = new ClientRequest(messageStore, socket);
                    clientRequest.start();

                //  executorService.execute(countDownLatch::countDown);

                }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
