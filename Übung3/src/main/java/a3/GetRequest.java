package a3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.concurrent.CountDownLatch;


public class GetRequest extends Thread {

    private final Socket socket;
    private final CountDownLatch countDownLatch;

    public GetRequest(Socket socket, CountDownLatch countDownLatch) {
        this.socket = socket;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {

        try {

            // Get message
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String input;

            do {
                input = reader.readLine();

                // Print input to console
                if (input != null)
                    System.out.println(input);
            } while (input != null && !input.equalsIgnoreCase("Bye"));

            // decrement countDownLatch
            countDownLatch.countDown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}