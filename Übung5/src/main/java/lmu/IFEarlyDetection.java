package lmu;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IFEarlyDetection extends Remote {

    Report analyze (XRayPicture xRay) throws RemoteException;

}
