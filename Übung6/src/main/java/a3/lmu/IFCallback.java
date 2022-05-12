package a3.lmu;

import a3.lmu.Report;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IFCallback extends Remote {

    public void setReport(Report report) throws RemoteException;

}
