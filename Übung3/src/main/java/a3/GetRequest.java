package a3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.concurrent.CountDownLatch;

public class GetRequest implements Runnable {

    private final Socket socket;
    private final CountDownLatch countDownLatch;

    public GetRequest(Socket socket, CountDownLatch countDownLatch) {
        this.socket = socket;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {

        try {
         //   InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String eingang;

            do {
                eingang = reader.readLine();

                if (eingang != null)
                    System.out.println(eingang);
            } while (eingang != null && !eingang.equalsIgnoreCase("Bye"));

            countDownLatch.countDown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}