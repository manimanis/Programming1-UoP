package org.manisoft.forms;

import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import org.manisoft.containers.StudentList;
import org.manisoft.entities.Student;
import org.manisoft.models.StudentsModel;

/**
 *
 * @author manianis
 */
public class PanelStudents extends JPanel 
        implements DataManagementInterface<Student> {

    private StudentsModel studentsModel = new StudentsModel();
    private StudentList studentList = null;

    /**
     * Creates new form PanelStudents
     */
    public PanelStudents() {
        initComponents();
    }

    public StudentList getStudentList() {
        return studentList;
    }

    public void setStudentList(StudentList studentList) {
        this.studentList = studentList;
        studentsModel.setStudentList(studentList);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollPane = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        ctrlPanel = new javax.swing.JPanel();
        addBtn = new javax.swing.JButton();
        editBtn = new javax.swing.JButton();
        removeBtn = new javax.swing.JButton();

        table.setModel(studentsModel);
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        scrollPane.setViewportView(table);

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE)
                    .addComponent(ctrlPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ctrlPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public Student addNew() {
        Student student = new Student();
        student.setID("-");
        JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        DialogStudent dlg = new DialogStudent(topFrame, OperationType.ADD);
        dlg.setLocationRelativeTo(topFrame);
        dlg.setData(student);
        dlg.setTitle("New Student");
        dlg.setVisible(true);
        if (dlg.getDialogResult() == JOptionPane.CANCEL_OPTION) {
            return null;
        }
        student.setID(studentList.genStudentID());
        return student;
    }

    @Override
    public Student editItem(int index) {
        Student student = (Student) studentList.get(index).clone();
        JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        DialogStudent dlg = new DialogStudent(topFrame, OperationType.EDIT);
        dlg.setLocationRelativeTo(topFrame);
        dlg.setData(student);
        dlg.setTitle("Edit Student");
        dlg.setVisible(true);
        if (dlg.getDialogResult() == JOptionPane.CANCEL_OPTION) {
            return null;
        }
        return student;
    }

    @Override
    public Student removeItem(int index) {
        Student student = (Student) studentList.get(index).clone();
        int res = JOptionPane.showConfirmDialog(
                null, "Do you want to delete this student?",
                "Delete a Student",
                JOptionPane.YES_NO_OPTION);
        if (res == JOptionPane.NO_OPTION) {
            return null;
        }
        return student;
    }

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        Student student = addNew();
        if (student != null) {
            studentList.add(student);
            studentsModel.fireTableDataChanged();
            FrameMain.frameMain.saveData();
        }
    }//GEN-LAST:event_addBtnActionPerformed

    private void editBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBtnActionPerformed
        int selItem = table.getSelectedRow();
        if (selItem == -1) {
            return;
        }
        int rowIndex = table.convertRowIndexToModel(selItem);
        Student student = editItem(rowIndex);
        if (student != null) {
            studentList.set(rowIndex, student);
            studentsModel.fireTableRowsUpdated(rowIndex, rowIndex);
            FrameMain.frameMain.saveData();
        }
    }//GEN-LAST:event_editBtnActionPerformed

    private void removeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeBtnActionPerformed
        int selItem = table.getSelectedRow();
        if (selItem == -1) {
            return;
        }
        int rowIndex = table.convertRowIndexToModel(selItem);
        Student student = removeItem(rowIndex);
        if (student != null) {
            studentList.remove(rowIndex);
            studentsModel.fireTableRowsDeleted(selItem, selItem);
            FrameMain.frameMain.saveData();
        }
    }//GEN-LAST:event_removeBtnActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        if (evt.getButton() == MouseEvent.BUTTON1 && evt.getClickCount() == 2) {
            editBtnActionPerformed(null);
        }
    }//GEN-LAST:event_tableMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JPanel ctrlPanel;
    private javax.swing.JButton editBtn;
    private javax.swing.JButton removeBtn;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables

}
