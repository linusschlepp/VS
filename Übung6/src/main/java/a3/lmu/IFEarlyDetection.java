package a3.lmu;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IFEarlyDetection extends Remote {

   void analyze (XRayPicture xRay, IFCallback callback) throws RemoteException;


}
