import java.util.ArrayList;
import java.util.List;

public class Student extends Person {
    private String education;
    private List<GradeInfo> gradeReport;

    public Student(int id, String name, String email, String education) {
        super(id, name, email);
        this.education = education;
        this.gradeReport = new ArrayList<>();
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public List<GradeInfo> getGradeReport() {
        return gradeReport;
    }

    public void addGrade(String courseName, double grade) {
        gradeReport.add(new GradeInfo(courseName, grade));
    }

    public double getAverageGrade() {
        if (gradeReport.isEmpty()) {
            return 0.0;
        }

        double sum = 0.0;
        for (GradeInfo grade : gradeReport) {
            sum += grade.getGrade();
        }
        return sum / gradeReport.size();
    }

    @Override
    public String toString() {
        return String.format("%-10d %-25s %-30s %-15s %.1f",
                getId(),
                getName(),
                getEmail(),
                education,
                getAverageGrade());
    }
}