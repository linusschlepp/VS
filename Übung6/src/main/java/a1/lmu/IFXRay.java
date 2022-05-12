package a1.lmu;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

public interface IFXRay extends Remote {


    public Date getDate() throws RemoteException;

    public void setDate(Date date) throws RemoteException;

    public String getPatientName() throws RemoteException;

    public void setPatientName(String patientName) throws RemoteException;

    public byte[] getRawData() throws RemoteException;

    public void setRawData(byte[] rawData) throws RemoteException;
}
