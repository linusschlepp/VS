package a1.lmu;

import java.io.Serializable;
import java.util.Date;

public class XRayPicture implements Serializable, IFXRay {

    private Date date;
    transient String patientName;
    private byte[] rawData;


    public XRayPicture(Date snapOn, String patientName, byte[] rawData) {
        this.date = snapOn;
        this.patientName = patientName;
        this.rawData = rawData;
    }

    public XRayPicture() {
    }
    @Override
    public Date getDate() {
        return date;
    }
    @Override
    public void setDate(Date date) {
        this.date = date;
    }
    @Override
    public String getPatientName() {
        return patientName;
    }
    @Override
    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }
    @Override
    public byte[] getRawData() {
        return rawData;
    }
    @Override
    public void setRawData(byte[] rawData) {
        this.rawData = rawData;
    }
}
