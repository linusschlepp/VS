package a2;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class SocketThread implements Runnable {


    private final Socket socket;

    SocketThread(Socket socket){
        this.socket = socket;
    }


    @Override
    public void run() {

        try{

            // Send message to server
            PrintWriter pw = new PrintWriter(socket.getOutputStream());

            // Read contents of index.html line by line
            List<String> lines = Files.readAllLines(Paths.get("src\\main\\java\\a2\\index.html"));

            StringBuilder sb = new StringBuilder();
            // Append html-header and number of characters of lines to StringBuilder
            sb.append("HTTP/1.1 200 OK\r\n").append("Content-Type: text/html\r\n").append("Content-Length: ")
                    .append(lines.stream().mapToInt(line -> line.length() + 1).sum()).append("\r\n\r\n");

            // Append content of index.html to StringBuilder
            lines.forEach(sb::append);
            pw.println(sb);
            pw.flush();


        }catch(Exception e){
            e.printStackTrace();
        }

        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
