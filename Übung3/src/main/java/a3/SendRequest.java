package a3;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

public class SendRequest extends Thread {

    private final Socket socket;
    private final CountDownLatch countDownLatch;

    public SendRequest(Socket socket, CountDownLatch countDownLatch) {
        this.socket = socket;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {


        try {

            // Send Message
            PrintWriter writer = new PrintWriter(socket.getOutputStream());

            String input;

            do {
                // Get input of user
                input = new Scanner(System.in).nextLine();

                writer.println(input);
                writer.flush();
                // if input equals "bye" break out of loop
            } while (!input.equalsIgnoreCase("bye"));


            // Decrement CountDownLatch
            countDownLatch.countDown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}