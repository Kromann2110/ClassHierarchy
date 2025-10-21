import java.io.Serializable; // <-- 1. ADD THIS IMPORT

public class GradeInfo implements Serializable { // <-- 2. ADD 'implements Serializable'

    private static final long serialVersionUID = 1L; // <-- 3. ADD THIS LINE

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