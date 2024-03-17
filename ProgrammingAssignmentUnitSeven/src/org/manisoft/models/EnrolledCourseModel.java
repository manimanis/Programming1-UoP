package org.manisoft.models;

import javax.swing.table.AbstractTableModel;
import org.manisoft.containers.EnrollmentList;
import org.manisoft.entities.EnrolledCourse;

/**
 * JTable Model to display the enrolled courses data.
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
    
    /**
     * Get the number of enrollments.
     * @return 
     */
    @Override
    public int getRowCount() {
        if (courseList == null) {
            return 0;
        }
        return courseList.size();
    }

    /**
     * Get the number of columns in the table.
     * @return 
     */
    @Override
    public int getColumnCount() {
        return columns.length;
    }

    /**
     * Get columns labels.
     * @param column column index.
     * @return Column label.
     */
    @Override
    public String getColumnName(int column) {
        return columns[column];
    }
    
    /**
     * Get table's cell content.
     * @param rowIndex row index
     * @param columnIndex column index
     * @return 
     */
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
