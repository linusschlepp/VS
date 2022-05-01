package lmu;

import java.io.Serializable;
import java.util.Date;

public class XRayPicture implements Serializable {

    private Date snapOn;
    transient String patientName;
    private byte[] rawData;


    public XRayPicture(Date snapOn, String patientName, byte[] rawData) {
        this.snapOn = snapOn;
        this.patientName = patientName;
        this.rawData = rawData;
    }

    public XRayPicture() {
    }

    public Date getSnapOn() {
        return snapOn;
    }

    public void setSnapOn(Date snapOn) {
        this.snapOn = snapOn;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public byte[] getRawData() {
        return rawData;
    }

    public void setRawData(byte[] rawData) {
        this.rawData = rawData;
    }
}
