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

            StringBuilder sb = new StringBuilder();
            System.out.println("Warte auf Verbindungen...");
            Socket socket = serverSocket.accept();

            // Get messages
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // Send messages
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

            sb.append("Hi, I'm crazy davinci. Nice you made it so far.\n").append("Please tell me your name!");
            writer.println(sb);
            writer.flush();

            sb.setLength(0);
            String name = reader.readLine();
            sb.append("Welcome ").append(name).append(". It's a pleasure talking to you.\n")
                    .append("I'm going to respond to every text you send and I'll send a ping from time to time.\n")
                    .append("You can end by sending the word 'Bye' or by closing the socket.\n").append("I will close the socket after 2 minutes");
            writer.println(sb);
            writer.flush();

            ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
            // Add Runnable to ScheduledExecutorService and execute with certain delay
            executorService.scheduleAtFixedRate(new PingUpdate(writer), 5, 10, TimeUnit.SECONDS);

            String input;
            final long oneSecond = 1000 * 1000 * 1000; // one billion nano-seconds
            long startTime = System.nanoTime();

            do {
                input = reader.readLine();
                writer.println(input);
                writer.flush();
                // do loop, while delta between start and current time is under two minutes
            } while (((System.nanoTime() - startTime) < 2 * 60 * oneSecond) && input != null && !input.equalsIgnoreCase("bye"));

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
            SimpleDateFormat timeStamp = new SimpleDateFormat("MMM dd HH:mm:ss");
            writer.println("It is now " + timeStamp.format(new Date()) + "CEST");
            writer.flush();
        }
    }

}