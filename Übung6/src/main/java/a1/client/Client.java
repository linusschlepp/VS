package a1.client;


import a1.lmu.IFEarlyDetection;
import a1.lmu.IFXRay;
import a1.lmu.Report;
import a1.lmu.XRayPicture;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;

public class Client {




    public static void main(String[] args) {

        try {
            Registry r = LocateRegistry.getRegistry("localhost", 1099);
            IFEarlyDetection earlyDetection = (IFEarlyDetection) r.lookup("EDT");

            BufferedImage bI = ImageIO.read(new File("src/main/resources/images/red.png"));

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(bI, "PNG", byteArrayOutputStream);

            XRayPicture xRayPicture = new XRayPicture(new Date(), "Kurt von der Huefte", byteArrayOutputStream.toByteArray());
            IFXRay ifxRay = (IFXRay) UnicastRemoteObject.exportObject(xRayPicture, 0);
            Report report = earlyDetection.analyze(ifxRay);

            System.out.println(report);
        } catch (NotBoundException | IOException e) {
            e.printStackTrace();
        }


    }


}
