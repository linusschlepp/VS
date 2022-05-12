package a2.lmu;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

public interface IFReport extends Remote {



    public Date getDate() throws RemoteException;

    public void setDate(Date date) throws RemoteException;

    public String getDiagnosis() throws RemoteException;

    public void setDiagnosis(String diagnosis) throws RemoteException;

    public String getFurtherSteps() throws RemoteException;

    public void setFurtherSteps(String furtherSteps) throws RemoteException;
}
