package a3;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Server {

    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(8080)) {

            System.out.println("Warte auf Verbindungen...");
            Socket socket = serverSocket.accept();

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

            writer.println("Hi, I'm crazy davinci. Nice you made it so far.");
            writer.println("Please tell me your name!");
            writer.flush();

            String name = reader.readLine();
            writer.println("Welcome " + name + ". It's a pleasure talking to you.");
            writer.println("I'm going to respond to every text you send and I'll send a ping from time to time.");
            writer.println("You can end by sending the word 'Bye' or by closing the socket.");
            writer.println("I will close the socket after 2 minutes");
            writer.flush();

            ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
            executorService.scheduleAtFixedRate(new PingUpdate(writer), 5, 10, TimeUnit.SECONDS);

            String eingang;
            final long NANOSEC_PER_SEC = 1000 * 1000 * 1000;
            long startTime = System.nanoTime();

            do {
                eingang = reader.readLine();
                writer.println(eingang);
                writer.flush();
            } while (((System.nanoTime() - startTime) < 2 * 60 * NANOSEC_PER_SEC) && eingang != null && !eingang.equalsIgnoreCase("bye"));

            System.out.println("Beende Server...");
            executorService.shutdown();
            serverSocket.close();
            socket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    static class PingUpdate implements Runnable {

        private final PrintWriter writer;

        public PingUpdate(PrintWriter writer) {
            this.writer = writer;
        }

        @Override
        public void run() {
            SimpleDateFormat timestamp = new SimpleDateFormat("MMM dd HH:mm:ss");
            writer.println("It is now " + timestamp.format(new Date()) + " CEST");
            writer.flush();
        }
    }

}