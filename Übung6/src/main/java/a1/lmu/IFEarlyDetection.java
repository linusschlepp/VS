package a1.lmu;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IFEarlyDetection extends Remote {

    Report analyze (IFXRay ifxRay) throws RemoteException;

}