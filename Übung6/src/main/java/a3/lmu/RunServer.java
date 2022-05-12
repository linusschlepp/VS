package a3.lmu;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

public class RunServer implements Runnable, Serializable {

    private final XRayPicture xRay;
    private final IFCallback callback;

    public RunServer(XRayPicture xRay, IFCallback callback) {
        this.xRay = xRay;
        this.callback = callback;
    }

    @Override
    public void run() {


        Report report = new Report();
        report.setDate(new Date());


        try {
            Thread.sleep(1000);
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

            callback.setReport(report);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }
}

