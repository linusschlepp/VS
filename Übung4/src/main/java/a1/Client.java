package a1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


public class Client {


    public static void main(String[] args) {


        try {


            Socket socket = new Socket("localhost", 8080);
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter pw = new PrintWriter(socket.getOutputStream());
            while (true) {
                System.out.println("Greetings, what do you wanna do?");
                System.out.println("1: Registration");
                System.out.println("2: Send messages");
                System.out.println("3: Print messages");

                String protocol = switch (new Scanner(System.in).nextInt()) {
                    case 1 -> "REG#";
                    case 2 -> "SND#";
                    case 3 -> "RCV#";
                    case 4 -> "CHK";
                    default -> "";
                };

                if (!protocol.equals("CHK")) {
                    System.out.println("From?");
                    protocol += new Scanner(System.in).nextLine();
                    if (protocol.startsWith("SND#")) {
                        System.out.println("To who?");
                        protocol += "#" + new Scanner(System.in).nextLine();
                        System.out.println("Message?");
                        protocol += "#" + new Scanner(System.in).nextLine();
                    }
                }


                pw.println(protocol);
                pw.flush();

                System.out.println(br.readLine());

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
