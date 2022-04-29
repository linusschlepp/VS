package a1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class ClientRequest extends Thread {


    private final MessageStore messageStore;
    private final Socket socket;



    public ClientRequest(MessageStore messageStore, Socket socket) {
        this.messageStore = messageStore;
        this.socket = socket;

    }

    @Override
    public void run() {

        try {

            PrintWriter pw = new PrintWriter(socket.getOutputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));

            while(true) {

                String[] message = br.readLine().split("#");
                switch (message[0]) {
                    case "REG":
                        if (this.messageStore.notDuplicate(message[1])) {
                            this.messageStore.addUser(message[1]);
                            pw.println("Welcome, " + message[1] + "!");
                        } else
                            pw.println(message[1] + " is already registered");
                        break;
                    case "SND":
                        if (message.length == 4) {
                            pw.println(this.messageStore.sendMessage(message[1], message[2], message[3]));
                        } else
                            pw.println("Error!");
                        break;
                    case "RCV":
                        if (this.messageStore.notDuplicate(message[1]))
                            pw.println(message[1]
                                    + " is not yet registered");
                        else
                            pw.println(this.messageStore.getMessages(message[1]));
                        break;
                    case "CHK":
                        pw.println(this.messageStore.printUsers());
                        break;
                    default:
                        pw.println("Something went wrong, please try again" + message[0]);
                        break;
                }
                pw.flush();


            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
