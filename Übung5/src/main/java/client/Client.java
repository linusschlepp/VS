package client;


import lmu.IFEarlyDetection;
import lmu.Report;
import lmu.XRayPicture;
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


    // variable only exists for testing purposes
    public static String strForTest;

    public static void main(String[] args) {

        try {
            Registry r = LocateRegistry.getRegistry("localhost", 1099);
            IFEarlyDetection earlyDetection = (IFEarlyDetection) r.lookup("EDT");

            // if tests are running, it takes the first element of the args-array, if not it's the blue-image
            BufferedImage bI = args.length == 0 ? ImageIO.read(new File("src/main/resources/images/blue.png")) : ImageIO.read(new File(args[0]));

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(bI, "PNG", byteArrayOutputStream);

            Report report = earlyDetection.analyze(new XRayPicture(new Date(), "Kurt von der Huefte", byteArrayOutputStream.toByteArray()));

            // strForTest gets set
            strForTest = report.getDiagnosis();

            System.out.println(report);
        } catch (NotBoundException | IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * Method gets called by testing methods, to assert if expected diagnosis are true
     *
     * @return diagnosis of the report from the main method
     */
    public static String getDiagnosis(){
        return strForTest;
    }
}
