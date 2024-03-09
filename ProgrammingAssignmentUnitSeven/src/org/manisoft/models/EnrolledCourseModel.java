package org.manisoft.models;

import javax.swing.table.AbstractTableModel;
import org.manisoft.containers.CourseList;
import org.manisoft.containers.EnrollmentList;
import org.manisoft.entities.Course;
import org.manisoft.entities.EnrolledCourse;

/**
 *
 * @author manianis
 */
public class EnrolledCourseModel extends AbstractTableModel {

    public static final String[] columns = new String[]{
        "ID", "Name", "Average Grade"
    };
    private EnrollmentList courseList = null;

    public EnrollmentList getCourseList() {
        return courseList;
    }

    public void setCourseList(EnrollmentList courseList) {
        this.courseList = courseList;
        fireTableDataChanged();
    }
    
    @Override
    public int getRowCount() {
        if (courseList == null) {
            return 0;
        }
        return courseList.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        EnrolledCourse course = courseList.get(rowIndex);
        if (columnIndex == 0) {
            return course.getStudent().getID();
        } else if (columnIndex == 1) {
            return course.getStudent().getName();
        } else if (columnIndex == 2) {
            return course.getAvgGrade();
        } 
        return "";
    }

}
