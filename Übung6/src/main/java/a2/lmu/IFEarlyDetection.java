package a2.lmu;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IFEarlyDetection extends Remote {

    IFReport analyze (XRayPicture xRay) throws RemoteException;

}
