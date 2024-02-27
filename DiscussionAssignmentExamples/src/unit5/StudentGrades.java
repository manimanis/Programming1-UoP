package unit5;

/**
 *
 * @author Cyberbox
 */
public class StudentGrades {

    private double[] grades;

    public static final double NOT_SET = 55.55;
    public static final double ABSENT = 99.99;

    public static boolean isNonNumericGrade(double grade) {
        return grade == NOT_SET || grade == ABSENT;
    }

    public static boolean isValidGrade(double grade) {
        return (grade >= 0.0 && grade <= 20.0)
                || grade == NOT_SET
                || grade == ABSENT;
    }

    public static String gradeToString(double grade) {
        if (grade == NOT_SET || !isValidGrade(grade)) {
            return "--.--";
        } else if (grade == ABSENT) {
            return "ABSENT";
        }
        return String.format("%5.2f", grade);
    }

    public StudentGrades(int count) {
        if (count < 1) {
            count = 1;
        }
        grades = new double[count];
    }

    public int getGradesCount() {
        return grades.length;
    }

    public void setGrade(int index, double grade) {
        if (index >= 0 && index < grades.length) {
            if (!isValidGrade(grade)) {
                grade = NOT_SET;
            }
            grades[index] = grade;
        }
    }

    public double getGrade(int index) {
        if (index >= 0 && index < grades.length) {
            return grades[index];
        }
        return NOT_SET;
    }

    public double getAverage() {
        double s = 0, coef = 0;
        for (double grade : grades) {
            if (!isNonNumericGrade(grade)) {
                s += grade;
                coef++;
            }
        }
        if (coef == 0) {
            return 0.0;
        }
        return s / coef;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < grades.length; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(gradeToString(grades[i]));
        }
        return sb.toString();
    }

}
