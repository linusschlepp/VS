package entity;

public class ExamPerformance {


    private int id;
    private String examId;
    private int matrikelNr;
    private int attempt;
    private float grade;

    public ExamPerformance(int id, String examId, int matrikelNr, int attempt, float grade){
        this.id = id;
        this.examId = examId;
        this.matrikelNr = matrikelNr;
        this.attempt = attempt;
        this.grade = grade;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
    }

    public int getMatrikelNr() {
        return matrikelNr;
    }

    public void setMatrikelNr(int matrikelNr) {
        this.matrikelNr = matrikelNr;
    }

    public int getAttempt() {
        return attempt;
    }

    public void setAttempt(int attempt) {
        this.attempt = attempt;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }
}
