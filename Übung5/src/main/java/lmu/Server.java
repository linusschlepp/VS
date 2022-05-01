package lmu;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
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
    public Report analyze(XRayPicture xRay) throws RemoteException {

        Report report = new Report();
        report.setDate(new Date());


        try {
            BufferedImage bI = ImageIO.read(new ByteArrayInputStream(xRay.getRawData()));

            Color color = new Color(bI.getRGB(1,1), true);

            if (color.getRed() > 125) {
                report.setDiagnosis("Very bad");
                report.setFurtherSteps("Please come for checkup");
            } else if (color.getGreen() > 125) {
                report.setDiagnosis("Concerning signs");
                report.setFurtherSteps("Recommended checkup");
            } else {
                report.setDiagnosis("Everything is fine");
                report.setFurtherSteps("No need for checkup");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        return report;
    }
}
