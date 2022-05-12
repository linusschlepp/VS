package a3.lmu;


import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server implements IFEarlyDetection {

    public static void main(String[] args) {

        IFEarlyDetection server = new Server();
        try {
            IFEarlyDetection stub = (IFEarlyDetection) UnicastRemoteObject.exportObject(server, 0);
            Registry r = LocateRegistry.createRegistry(1099);
            r.bind("EDT", stub);
        } catch (RemoteException | AlreadyBoundException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void analyze(XRayPicture xRayPicture, IFCallback callback) throws RemoteException {

        RunServer runServer = new RunServer(xRayPicture, callback);
        ExecutorService executors = Executors.newSingleThreadExecutor();
        executors.execute(runServer);

    }
}
