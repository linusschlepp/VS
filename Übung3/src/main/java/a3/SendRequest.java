package a3;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

public class SendRequest implements Runnable {

    private final Socket socket;
    private final CountDownLatch countDownLatch;

    public SendRequest(Socket socket, CountDownLatch countDownLatch) {
        this.socket = socket;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {

        Scanner scanner = new Scanner(System.in);

        try {
          //  OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(new PrintWriter(socket.getOutputStream()));

            String eingabe;

            do {
                eingabe = scanner.nextLine();

                writer.println(eingabe);
                writer.flush();
            } while (!eingabe.equalsIgnoreCase("bye"));

            scanner.close();
            countDownLatch.countDown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}