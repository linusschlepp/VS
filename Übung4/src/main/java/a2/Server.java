package a2;



import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {

    public static void main(String[] args) {


        try (ServerSocket serverSocket = new ServerSocket(1234)) {


            UserStore userStore = new UserStore();


            while(true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected....  ");
                try {
                    ClientRequest clientRequest = new ClientRequest(userStore, socket);
                    clientRequest.start();
                }catch(NullPointerException ignored){}



            }

        }catch(IOException e){
            e.printStackTrace();
        }


    }
}
