package a2.lmu;

import java.io.Serializable;
import java.util.Date;

public class Report implements Serializable, IFReport {

    private Date date;
    private String diagnosis;
    private String furtherSteps;


    public Report() {
    }

    public Report(Date date, String diagnosis, String furtherSteps) {
        this.date = date;
        this.diagnosis = diagnosis;
        this.furtherSteps = furtherSteps;
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
    public String getDiagnosis() {
        return diagnosis;
    }

    @Override
    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    @Override
    public String getFurtherSteps() {
        return furtherSteps;
    }

    @Override
    public void setFurtherSteps(String furtherSteps) {
        this.furtherSteps = furtherSteps;
    }

    @Override
    public String toString() {
        return "Report{" +
                "date=" + date +
                ", diagnosis='" + diagnosis + '\'' +
                ", furtherSteps='" + furtherSteps + '\'' +
                '}';
    }
}
