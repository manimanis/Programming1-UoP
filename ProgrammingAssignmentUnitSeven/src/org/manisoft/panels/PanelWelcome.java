
package org.manisoft.panels;

import org.manisoft.forms.FrameMain;

/**
 *
 * @author Cyberbox
 */
public class PanelWelcome extends javax.swing.JPanel {

    /**
     * Creates new form PanelWelcom
     */
    public PanelWelcome() {
        initComponents();
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

        lblTitle = new javax.swing.JLabel();
        panelCmd = new javax.swing.JPanel();
        lblQuestion = new javax.swing.JLabel();
        btnStudents = new javax.swing.JButton();
        btnCourses = new javax.swing.JButton();
        btnEnrollments = new javax.swing.JButton();
        btnGrades = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        lblTitle.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(255, 51, 0));
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("Students Management System");
        lblTitle.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(lblTitle, java.awt.BorderLayout.PAGE_START);

        panelCmd.setLayout(new java.awt.GridBagLayout());

        lblQuestion.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblQuestion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblQuestion.setText("What do you want to do?");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        panelCmd.add(lblQuestion, gridBagConstraints);

        btnStudents.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnStudents.setText("Students Management");
        btnStudents.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStudentsActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        panelCmd.add(btnStudents, gridBagConstraints);

        btnCourses.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnCourses.setText("Courses Management");
        btnCourses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCoursesActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        panelCmd.add(btnCourses, gridBagConstraints);

        btnEnrollments.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnEnrollments.setText("Enrollments Management");
        btnEnrollments.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnrollmentsActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        panelCmd.add(btnEnrollments, gridBagConstraints);

        btnGrades.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnGrades.setText("Grades Management");
        btnGrades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGradesActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        panelCmd.add(btnGrades, gridBagConstraints);

        add(panelCmd, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnStudentsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStudentsActionPerformed
        FrameMain.frameMain.startStudentsManagement();
    }//GEN-LAST:event_btnStudentsActionPerformed

    private void btnCoursesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCoursesActionPerformed
        FrameMain.frameMain.startCoursesManagement();
    }//GEN-LAST:event_btnCoursesActionPerformed

    private void btnEnrollmentsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnrollmentsActionPerformed
        FrameMain.frameMain.startEnrollmentsManagement();
    }//GEN-LAST:event_btnEnrollmentsActionPerformed

    private void btnGradesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGradesActionPerformed
        FrameMain.frameMain.startGradesManagement();
    }//GEN-LAST:event_btnGradesActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCourses;
    private javax.swing.JButton btnEnrollments;
    private javax.swing.JButton btnGrades;
    private javax.swing.JButton btnStudents;
    private javax.swing.JLabel lblQuestion;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel panelCmd;
    // End of variables declaration//GEN-END:variables
}
