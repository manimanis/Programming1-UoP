package org.manisoft.models;

import javax.swing.table.AbstractTableModel;
import org.manisoft.containers.EnrollmentList;
import org.manisoft.entities.EnrolledCourse;

/**
 *
 * @author manianis
 */
public class GradesModel extends AbstractTableModel {
    
    private EnrollmentList courseList = null;
    
    public EnrollmentList getCourseList() {
        return courseList;
    }
    
    public void setCourseList(EnrollmentList courseList) {
        this.courseList = courseList;
        fireTableStructureChanged();
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
        if (courseList == null || courseList.isEmpty()) {
            return 0;
        }
        return courseList.get(0).getGradesCount() + 3;
    }
    
    @Override
    public String getColumnName(int column) {
        if (courseList == null || courseList.isEmpty()) {
            return "";
        }
        int gc = courseList.get(0).getGradesCount();
        if (column == 0) {
            return "ID";
        } else if (column == 1) {
            return "Name";
        } else if (column < gc + 2) {
            return "Grade " + (column - 1);
        }
        return "Average";
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        EnrolledCourse course = courseList.get(rowIndex);
        int gc = course.getGradesCount();
        if (columnIndex == 0) {
            return course.getStudent().getID();
        } else if (columnIndex == 1) {
            return course.getStudent().getName();
        } else if (columnIndex < gc + 2) {
            return course.getGrade(columnIndex - 2);
        }
        return course.getAvgGrade();        
    }
    
}
