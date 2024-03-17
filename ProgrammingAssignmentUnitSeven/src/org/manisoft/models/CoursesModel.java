package org.manisoft.models;

import javax.swing.table.AbstractTableModel;
import org.manisoft.containers.CourseList;
import org.manisoft.entities.Course;

/**
 * JTable Model to display courses data.
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
    
    /**
     * Get the number of courses.
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
        Course course = courseList.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> 
                course.getCode();
            case 1 -> 
                course.getName();
            case 2 -> 
                course.getCapacity();
            case 3 -> 
                course.getGradesCount();
            default -> 
                "";
        };
    }

}
