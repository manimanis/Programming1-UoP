package org.manisoft.panels;

import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import org.manisoft.containers.CourseList;
import org.manisoft.containers.EnrolledCoursesList;
import org.manisoft.containers.EnrollmentList;
import org.manisoft.containers.StudentList;
import org.manisoft.entities.Course;
import org.manisoft.entities.EnrolledCourse;
import org.manisoft.dialogs.DataManagementInterface;
import org.manisoft.dialogs.DialogEnrolledCourse;
import org.manisoft.forms.FrameMain;
import org.manisoft.dialogs.OperationType;
import org.manisoft.models.EnrolledCourseModel;

/**
 *
 * @author manianis
 */
public class PanelEnrolledCourses extends JPanel
        implements DataManagementInterface<EnrolledCourse> {
    
    private EnrolledCourseModel enrolledCoursesModel = new EnrolledCourseModel();
    private StudentList studentList = null;
    private EnrolledCoursesList enrolledCoursesList = null;
    private CourseList courseList = null;
    
    private Course selectedCourse = null;
    private EnrollmentList enrollmentList = null;

    /**
     * Creates new form PanelStudents
     */
    public PanelEnrolledCourses() {
        initComponents();
    }
    
    public EnrolledCoursesList getEnrolledCoursesList() {
        return enrolledCoursesList;
    }
    
    public void setEnrolledCoursesList(EnrolledCoursesList courseList) {
        this.enrolledCoursesList = courseList;
        setSelectedCourse(comboCourses.getSelectedIndex());
    }
    
    public StudentList getStudentList() {
        return studentList;
    }
    
    public void setStudentList(StudentList studentList) {
        this.studentList = studentList;
    }
    
    public CourseList getCourseList() {
        return courseList;
    }
    
    public void setCourseList(CourseList courseList) {
        this.courseList = courseList;
        initCoursesCombo();
    }
    
    private void initCoursesCombo() {
        comboCourses.removeAllItems();
        for (Course course : courseList) {
            comboCourses.addItem(course.toString().trim());
        }
        if (!courseList.isEmpty()) {
            setSelectedCourse(0);
        }
    }
    
    @Override
    public EnrolledCourse addNew() {
        EnrolledCourse course = new EnrolledCourse(selectedCourse);
        StudentList availStudentsList = StudentList.findNotEnrolledStudents(
                studentList, enrollmentList);
        JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        DialogEnrolledCourse dlg = new DialogEnrolledCourse(
                topFrame, OperationType.ADD);
        dlg.setLocationRelativeTo(topFrame);
        dlg.setStudentList(availStudentsList);
        dlg.setData(course);
        dlg.setTitle("Enroll a student");
        dlg.setVisible(true);
        if (dlg.getDialogResult() == JOptionPane.CANCEL_OPTION) {
            return null;
        }
        return dlg.getData();
    }
    
    @Override
    public EnrolledCourse editItem(int index) {
        EnrolledCourse course = (EnrolledCourse) enrollmentList.get(index).clone();
        JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        StudentList availStudentsList = StudentList.findNotEnrolledStudents(
                studentList, enrollmentList);
        availStudentsList.add(course.getStudent());
        DialogEnrolledCourse dlg = new DialogEnrolledCourse(topFrame, OperationType.EDIT);
        dlg.setLocationRelativeTo(topFrame);
        dlg.setStudentList(availStudentsList);
        dlg.setData(course);
        dlg.setTitle("Edit Enrolled Student");
        dlg.setVisible(true);
        if (dlg.getDialogResult() == JOptionPane.CANCEL_OPTION) {
            return null;
        }
        return dlg.getData();
    }
    
    @Override
    public EnrolledCourse removeItem(int index) {
        EnrolledCourse course = (EnrolledCourse) enrollmentList.get(index);
        int res = JOptionPane.showConfirmDialog(
                null, "Do you want to cancel this enrollment?",
                "Cancel a Student's Enrollment",
                JOptionPane.YES_NO_OPTION);
        if (res == JOptionPane.NO_OPTION) {
            return null;
        }
        return course;
    }
    
    public void setSelectedCourse(int index) {
        if (index >= 0 && index < courseList.size()) {
            setSelectedCourse(courseList.get(index));
        }
    }
    
    public void setSelectedCourse(Course selectedCourse) {
        this.selectedCourse = selectedCourse;
        if (selectedCourse != null && enrolledCoursesList != null) {
            enrollmentList = enrolledCoursesList.enrollByCourse(selectedCourse);
            enrolledCoursesModel.setCourseList(enrollmentList);
        }
    }
    
    public Course getSelectedCourse() {
        return selectedCourse;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        scrollPane = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        ctrlPanel = new javax.swing.JPanel();
        addBtn = new javax.swing.JButton();
        editBtn = new javax.swing.JButton();
        removeBtn = new javax.swing.JButton();
        comboCourses = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();

        java.awt.GridBagLayout layout = new java.awt.GridBagLayout();
        layout.rowHeights = new int[] {35, 22, 0, 32};
        layout.columnWeights = new double[] {1.0};
        setLayout(layout);

        table.setAutoCreateRowSorter(true);
        table.setModel(enrolledCoursesModel);
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        scrollPane.setViewportView(table);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 508;
        gridBagConstraints.ipady = 225;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(scrollPane, gridBagConstraints);

        addBtn.setText("New");
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });

        editBtn.setText("Edit");
        editBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBtnActionPerformed(evt);
            }
        });

        removeBtn.setText("Remove");
        removeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ctrlPanelLayout = new javax.swing.GroupLayout(ctrlPanel);
        ctrlPanel.setLayout(ctrlPanelLayout);
        ctrlPanelLayout.setHorizontalGroup(
            ctrlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ctrlPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(addBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(removeBtn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        ctrlPanelLayout.setVerticalGroup(
            ctrlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ctrlPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ctrlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addBtn)
                    .addComponent(editBtn)
                    .addComponent(removeBtn))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 283;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(ctrlPanel, gridBagConstraints);

        comboCourses.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboCourses.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboCoursesItemStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 452;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(comboCourses, gridBagConstraints);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Courses Enrollment Management");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(jLabel1, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        EnrolledCourse course = addNew();
        if (course != null) {
            enrollmentList.add(course);
            enrolledCoursesList.enrollToCourse(course);
            enrolledCoursesModel.fireTableDataChanged();
            FrameMain.frameMain.saveData();
        }
    }//GEN-LAST:event_addBtnActionPerformed

    private void editBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBtnActionPerformed
        int selItem = table.getSelectedRow();
        if (selItem == -1) {
            return;
        }
        int rowIndex = table.convertRowIndexToModel(selItem);
        EnrolledCourse course = editItem(rowIndex);
        if (course != null) {
            enrollmentList.set(rowIndex, course);
            EnrolledCourse oldCourse = enrollmentList.get(rowIndex);
            enrolledCoursesList.leaveFromCourse(oldCourse);
            enrolledCoursesList.enrollToCourse(course);
            enrolledCoursesModel.fireTableRowsUpdated(rowIndex, rowIndex);
            FrameMain.frameMain.saveData();
        }
    }//GEN-LAST:event_editBtnActionPerformed

    private void removeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeBtnActionPerformed
        int selItem = table.getSelectedRow();
        if (selItem == -1) {
            return;
        }
        int rowIndex = table.convertRowIndexToModel(selItem);
        EnrolledCourse course = removeItem(rowIndex);
        if (course != null) {
            enrolledCoursesList.leaveFromCourse(course);
            enrollmentList.remove(course);
            enrolledCoursesModel.fireTableRowsDeleted(selItem, selItem);
            FrameMain.frameMain.saveData();
        }
    }//GEN-LAST:event_removeBtnActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        if (evt.getButton() == MouseEvent.BUTTON1
                && evt.getClickCount() == 2) {
            editBtnActionPerformed(null);
        }
    }//GEN-LAST:event_tableMouseClicked

    private void comboCoursesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboCoursesItemStateChanged
        setSelectedCourse(comboCourses.getSelectedIndex());
    }//GEN-LAST:event_comboCoursesItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JComboBox<String> comboCourses;
    private javax.swing.JPanel ctrlPanel;
    private javax.swing.JButton editBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton removeBtn;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
