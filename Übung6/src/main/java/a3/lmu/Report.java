package a3.lmu;

import java.io.Serializable;
import java.util.Date;

public class Report implements Serializable {

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getFurtherSteps() {
        return furtherSteps;
    }

    public void setFurtherSteps(String furtherSteps) {
        this.furtherSteps = furtherSteps;
    }

    public boolean isEmpty(){

        return getFurtherSteps() == null && getDate() == null && getDiagnosis() == null;
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
