package a3;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.CountDownLatch;

public class Client {

    public static void main(String[] args) throws IOException, InterruptedException {



        Socket socket = new Socket("localhost", 8080);

        CountDownLatch countDownLatch = new CountDownLatch(2);

        // Receives messages
        GetRequest getRequest = new GetRequest(socket, countDownLatch);
        // Sends messages
        SendRequest sendRequest = new SendRequest(socket, countDownLatch);


        getRequest.start();
        sendRequest.start();

        countDownLatch.await();
        socket.close();
    }
}