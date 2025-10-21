import java.io.Serializable;

public class GradeInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String courseName;
    private double grade;

    public GradeInfo(String courseName, double grade) {
        this.courseName = courseName;
        this.grade = grade;
    }

    public String getCourseName() {
        return courseName;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }
}