package org.manisoft.models;

import javax.swing.table.AbstractTableModel;
import org.manisoft.containers.CourseList;
import org.manisoft.containers.StudentList;
import org.manisoft.entities.Course;
import org.manisoft.entities.Student;

/**
 *
 * @author manianis
 */
public class CoursesModel extends AbstractTableModel {

    public static final String[] columns = new String[]{
        "Code", "Name", "Capacity", "Grades Count"
    };
    private CourseList courseList = null;

    public CourseList getCourseList() {
        return courseList;
    }

    public void setCourseList(CourseList courseList) {
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
        Course course = courseList.get(rowIndex);
        if (columnIndex == 0) {
            return course.getCode();
        } else if (columnIndex == 1) {
            return course.getName();
        } else if (columnIndex == 2) {
            return course.getCapacity();
        } else if (columnIndex == 3) {
            return course.getGradesCount();
        } 
        return "";
    }

}
