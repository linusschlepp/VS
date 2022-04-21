package a3;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.CountDownLatch;

public class Client {

    public static void main(String[] args) throws IOException, InterruptedException {



        Socket socket = new Socket("localhost", 8080); // Eigener Server

        CountDownLatch countDownLatch = new CountDownLatch(2);

        GetRequest getRequest = new GetRequest(socket, countDownLatch);
        SendRequest sendRequest = new SendRequest(socket, countDownLatch);

        Thread empfangen = new Thread(getRequest);
        Thread senden = new Thread(sendRequest);
        empfangen.start();
        senden.start();

        countDownLatch.await();
        socket.close();
    }
}