package a2;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;


public class ClientRequest extends Thread {


    private final UserStore userStore;
    private final Socket socket;


    public ClientRequest(UserStore userStore, Socket socket) {
        this.userStore = userStore;
        this.socket = socket;

    }


    @Override
    public void run() {


        try {


            PrintWriter pw = new PrintWriter(socket.getOutputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));


            while (true) {


                //   String message = "";
                String[] message = null;

                try {
                    // message = br.readLine();
                    message = br.readLine().split("#");
                } catch (SocketException ignored) {
                }


                assert message != null;
                message[0] = (message[0] == null ? "EXIT" : message[0]);


                switch (message[0]) {

                    case "OPEN":
                        if (!this.userStore.isDuplicate(message[1])) {
                            pw.println("ADMN#" + "Welcome, " + message[1] + "!");
                            pw.println("ADMN#" + "Users online: " + this.userStore.printUsers());
                            this.userStore.addUser(message[1], this.socket);
                            printToOtherSockets("ADMN#" + message[1] + " entered the chat");
                        } else {
                            pw.println("ADMN#" + message[1] + " already online!");
                            pw.println("EXIT");
                        }
                        break;
                    case "PUBL":
                        printToOtherSockets("SHOW#" + this.userStore.getUser(this.socket) + "#" +
                                message[1]);
                        break;
                    case "EXIT":
                        printToOtherSockets("ADMN#" + this.userStore.getUser(this.socket) + " left the chat");
                        this.userStore.deleteUser(this.userStore.getUser(this.socket));
                        this.socket.close();
                        break;
                    default:
                        pw.println("Something went wrong, please try again");
                        break;

                }
                pw.flush();


//                if (message.startsWith("OPEN#")) {
//                    if (!userStore.isDuplicate(message.replaceAll("OPEN#", ""))) {
//                        pw.println("ADMN#" + "Welcome, " + message.replaceAll("OPEN#", "") + "!");
//                        pw.println("ADMN#" + "Users online: " + userStore.printUsers());
//                        userStore.addUser(message.replaceAll("OPEN#", ""), this.socket);
//                        printToOtherSockets("ADMN#" + message.replaceAll("OPEN#", "") + " entered the chat");
//                    } else {
//                        pw.println("ADMN#" + message.replaceAll("OPEN#", "") + " already online!");
//                        pw.println("EXIT");
//                    }
//                } else if (message.startsWith("PUBL#"))
//                    printToOtherSockets("SHOW#" + userStore.getUser(this.socket) + "#" +
//                            message.replaceAll("PUBL#", ""));
//                else if (message.equals("EXIT")) {
//                    printToOtherSockets("ADMN#" + this.userStore.getUser(this.socket) + " left the chat");
//                    this.userStore.deleteUser(this.userStore.getUser(this.socket));
//                    this.socket.close();
//                } else
//                    pw.println("Something went wrong, please try again");

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private void printToOtherSockets(String messageDistribute) {
        this.userStore.getSockets(this.socket).forEach(sock -> {
            try {
                PrintWriter printWriter = new PrintWriter(sock.getOutputStream());
                printWriter.println(messageDistribute);
                printWriter.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
