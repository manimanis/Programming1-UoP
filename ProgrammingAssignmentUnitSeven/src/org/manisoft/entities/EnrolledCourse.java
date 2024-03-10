
package org.manisoft.entities;

import org.manisoft.utilities.StrUtil;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

/**
 *
 * @author manianis
 */
public class EnrolledCourse implements Serializable, Comparable<EnrolledCourse> {
    private Course course;
    private Student student;
    private int[] grades;
    private int avgGrade = -1;

    public EnrolledCourse() {
        this(null, null, null);
    }  
    
    public EnrolledCourse(Course course) {
        this(course, null, new int[course.getGradesCount()]);
    }
    
    public EnrolledCourse(Course course, Student student) {
        this(course, student, new int[course.getGradesCount()]);
    }

    public EnrolledCourse(Course course, Student student, int[] grades) {
        this.course = course;
        this.student = student;
        this.grades = grades;
        calcAvgGrade();
    }   

    /**
     * The enrolled Course.
     * @return 
     */
    public Course getCourse() {
        return course;
    }

    /**
     * Enroll to the course.
     * @param course 
     */
    public void setCourse(Course course) {
        this.course = course;
    }

    /**
     * The enrolled Student.
     * @return 
     */
    public Student getStudent() {
        return student;
    }

    /**
     * Enroll a student to the course.
     * @param student 
     */
    public void setStudent(Student student) {
        this.student = student;
    }
    
    /**
     * Number of grades.
     * @return 
     */
    public int getGradesCount() {
        return grades.length;
    }
    
    /**
     * Change one student's grade.
     * @param index
     * @param grade 
     */
    public void setGrade(int index, int grade) {
        grades[index] = grade;
        calcAvgGrade();
    }
    
    /**
     * Get one student's grade.
     * @param index
     * @return 
     */
    public int getGrade(int index) {
        return grades[index];
    }
    
    /**
     * Recalc grade's average.
     */
    public void calcAvgGrade() {
        if (grades == null) {
            return;
        }
        
        int s = 0;
        for (int grade : grades) {
            s += grade;
        }
        avgGrade = s / grades.length;
    }
    
    /**
     * Return grades average.
     * @return 
     */
    public int getAvgGrade() {
        return avgGrade;
    }
    
    /**
     * Print enrolled course information in pretty formatting.
     */
    public void display() {
        System.out.println("\n*** Enrolled Course Information ***\n");
        
        student.display();
        System.out.println();
        course.display();
        System.out.println();
        System.out.println("Grades");
        System.out.println(StrUtil.duplicateStr("-", 20));
        for (int i = 0; i < grades.length; i++) {
            if (i > 0) System.out.print(", ");
            System.out.printf("%d: %3d", i+1, grades[i]);
        }
        System.out.println();
        System.out.printf("Average Grade: %3d\n", avgGrade);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.course);
        hash = 71 * hash + Objects.hashCode(this.student);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EnrolledCourse other = (EnrolledCourse) obj;
        if (!Objects.equals(this.course, other.course)) {
            return false;
        }
        return Objects.equals(this.student, other.student);
    }

    @Override
    public int compareTo(EnrolledCourse o) {
        if (o == this) {
            return 0;
        }
        if (o == null) {
            return 1;
        }
        int cmp = course.compareTo(o.course);
        if (cmp != 0) {
            return cmp;
        }
        return student.compareTo(student);
    }

    @Override
    public Object clone() {
        int[] gradesCopy = Arrays.copyOf(grades, grades.length);
        return new EnrolledCourse(course, student, gradesCopy);
    }
    
    /**
     * Reads an EnrolledCourse from the input stream.
     *
     * @param ois
     * @throws IOException
     * @throws ClassNotFoundException
     */
    protected void readObject(ObjectInputStream ois)
            throws IOException, ClassNotFoundException {
        this.course = (Course) ois.readObject();
        this.student = (Student) ois.readObject();
        int count = course.getGradesCount();
        this.grades = new int[count];
        for (int i = 0; i < count; i++) {
            this.grades[i] = ois.readInt();
        }
        calcAvgGrade();
    }

    /**
     * Writes an EnrolledCourse to the output stream.
     *
     * @param oos
     * @throws IOException
     */
    protected void writeObject(ObjectOutputStream oos)
            throws IOException {
        oos.writeObject(course);
        oos.writeObject(student);
        oos.writeInt(grades.length);
        for (int grade : grades) {
            oos.writeInt(grade);
        }
    }
}
