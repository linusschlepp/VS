package a2.lmu;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;

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
    public IFReport analyze(XRayPicture xRay) throws RemoteException {

        Report report = new Report();
        report.setDate(new Date());


        try {

            Color color = new Color(ImageIO.read(new ByteArrayInputStream(xRay.getRawData())).getRGB(1, 1), true);

            // if the red in the rgb value is higher than 50%, the diagnosis is not very pleasant
            if (color.getRed() > 125) {
                report.setDiagnosis("Very bad");
                report.setFurtherSteps("Please come for checkup");
            }
            // if the green in the rgb value is higher than 50%, the diagnosis might be concerning
            else if (color.getGreen() > 125) {
                report.setDiagnosis("Concerning signs");
                report.setFurtherSteps("Recommended checkup");
            }
            // if the blue in the rgb value is higher than 50%, everything is fine
            else {
                report.setDiagnosis("Everything is fine");
                report.setFurtherSteps("No need for checkup");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return (IFReport) UnicastRemoteObject.exportObject(report, 0);
    }
}
