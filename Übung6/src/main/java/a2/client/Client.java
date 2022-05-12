package a2.client;


import a1.lmu.IFXRay;
import a2.lmu.IFEarlyDetection;
import a2.lmu.IFReport;
import a2.lmu.Report;
import a2.lmu.XRayPicture;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Date;

public class Client {


    public static void main(String[] args) {

        try {
            Registry r = LocateRegistry.getRegistry("localhost", 1099);

            IFEarlyDetection earlyDetection = (IFEarlyDetection) r.lookup("EDT");

            BufferedImage bI =ImageIO.read(new File("src/main/resources/images/blue.png"));

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(bI, "PNG", byteArrayOutputStream);

           IFReport ifReport = earlyDetection.analyze(new XRayPicture(new Date(),
                           "Kurt von der Huefte", byteArrayOutputStream.toByteArray()));

            System.out.println(new Report(ifReport.getDate(), ifReport.getDiagnosis(), ifReport.getFurtherSteps()));
        } catch (NotBoundException | IOException e) {
            e.printStackTrace();
        }


    }


}
