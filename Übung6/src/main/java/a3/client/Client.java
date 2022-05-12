package a3.client;


import a3.lmu.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Client implements IFCallback {


    private static final Report report = new Report();

    public static void main(String[] args) {

        try {
            Registry r = LocateRegistry.getRegistry("localhost", 1099);
            IFEarlyDetection earlyDetection = (IFEarlyDetection) r.lookup("EDT");
            IFCallback callBack = new Client();

            IFCallback callBackStub = (IFCallback) UnicastRemoteObject.exportObject(callBack, 0);

            BufferedImage bI = ImageIO.read(new File("src/main/resources/images/blue.png"));

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(bI, "PNG", byteArrayOutputStream);

            long timeStart = System.currentTimeMillis();
            earlyDetection.analyze(new XRayPicture(new Date(), "Kurt von der Huefte", byteArrayOutputStream.toByteArray()),
                    callBackStub);

            while (report.isEmpty()) {
                TimeUnit.MILLISECONDS.sleep(50);
                System.out.print(".");
            }

            System.out.println("\nThe creation of the report took: " + (System.currentTimeMillis()-timeStart) + "ms");
            System.out.println(report);

        } catch (NotBoundException | IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void setReport(Report newReport) throws RemoteException {
        report.setDate(newReport.getDate());
        report.setDiagnosis(newReport.getDiagnosis());
        report.setFurtherSteps(newReport.getFurtherSteps());

    }
}
