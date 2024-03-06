package org.manisoft.models;

import javax.swing.table.AbstractTableModel;
import org.manisoft.containers.StudentList;
import org.manisoft.entities.Student;

/**
 *
 * @author manianis
 */
public class StudentsModel extends AbstractTableModel {

    public static final String[] columns = new String[]{
        "ID", "Name"
    };
    private StudentList studentList = null;

    public StudentList getStudentList() {
        return studentList;
    }

    public void setStudentList(StudentList studentList) {
        this.studentList = studentList;
        fireTableDataChanged();
    }
    
    @Override
    public int getRowCount() {
        if (studentList == null) {
            return 0;
        }
        return studentList.size();
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
        Student student = studentList.get(rowIndex);
        if (columnIndex == 0) {
            return student.getID();
        } else if (columnIndex == 1) {
            return student.getName();
        }
        return "";
    }

}
