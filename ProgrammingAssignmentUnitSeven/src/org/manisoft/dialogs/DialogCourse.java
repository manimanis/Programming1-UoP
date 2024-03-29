package org.manisoft.dialogs;

import java.awt.Frame;
import javax.swing.JOptionPane;
import org.manisoft.entities.Course;
import org.manisoft.utilities.StrUtil;

/**
 *
 * @author manianis
 */
public class DialogCourse extends DialogBase<Course> {

    /**
     * Creates new form DialogStudent
     *
     * @param parent
     * @param opType
     */
    public DialogCourse(Frame parent, OperationType opType) {
        super(parent, opType);
        initComponents();
    }

    /**
     * Update the dialog component from the data variable.
     */
    @Override
    public void updateInterface() {
        txtCode.setText(data.getCode());
        txtName.setText(data.getName());
        txtCapacity.setText(data.getCapacity() + "");
        txtGradesCount.setText(data.getGradesCount() + "");
    }

    /**
     * Update the data variable from the dialog content.
     */
    @Override
    public void updateData() {
        data.setCode(txtCode.getText());
        data.setName(txtName.getText());
        data.setCapacity(Integer.parseInt(txtCapacity.getText()));
        data.setGradesCount(Integer.parseInt(txtGradesCount.getText()));
    }

    /**
     * Verifies if the dialog's components are valid.
     * @return true if the dialog content is valid
     */
    @Override
    public boolean isValidData() {
        boolean error = false;
        
        String code = txtCode.getText();
        if (!StrUtil.isValidCourseCode(code)) {
            JOptionPane.showMessageDialog(null,
                    "Code is incorrect!",
                    "Incorrect Input",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }

        String name = StrUtil.toTitleCase(txtName.getText().trim());
        if (name.length() < 3 || name.length() > 32
                || !StrUtil.isAlphabetic(name, true)) {
            JOptionPane.showMessageDialog(null,
                    """
                    Name is incorrect!
                    Between 3 and 32 alphabetic characters.""",
                    "Incorrect Input",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        try {
            int capacity = Integer.parseInt(txtCapacity.getText());
            if (capacity < 1 || capacity > 50) {
                error = true;
            }
        }
        catch (NumberFormatException e) {
            error = true;
        }
        
        if (error) {
            JOptionPane.showMessageDialog(null,
                    """
                    Capacity is incorrect!
                    Must be an integer between 2 and 50.""",
                    "Incorrect Input",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        try {
            int gradesCount = Integer.parseInt(txtGradesCount.getText());
            if (gradesCount < 1 || gradesCount > 20) {
                error = true;
            }
        }
        catch (NumberFormatException e) {
            error = true;
        }
        
        if (error) {
            JOptionPane.showMessageDialog(null,
                    """
                    Grades count is incorrect!
                    Must be an integer between 2 and 20.""",
                    "Incorrect Input",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        return true;
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
        labelCode = new javax.swing.JLabel();
        txtCode = new javax.swing.JTextField();
        labelName = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        lblCapacity = new javax.swing.JLabel();
        txtCapacity = new javax.swing.JTextField();
        lblGradesCount = new javax.swing.JLabel();
        txtGradesCount = new javax.swing.JTextField();
        okBtn = new javax.swing.JButton();
        cancelBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        java.awt.GridBagLayout layout = new java.awt.GridBagLayout();
        layout.columnWidths = new int[] {80, 80, 80, 80};
        layout.columnWeights = new double[] {1.0, 1.0, 1.0, 1.0};
        layout.rowWeights = new double[] {1.0, 1.0, 1.0, 1.0};
        getContentPane().setLayout(layout);

        lblTitle.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("Course's Information");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(lblTitle, gridBagConstraints);

        labelCode.setText("Code");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(labelCode, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(txtCode, gridBagConstraints);

        labelName.setText("Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(labelName, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(txtName, gridBagConstraints);

        lblCapacity.setText("Capacity");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(lblCapacity, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(txtCapacity, gridBagConstraints);

        lblGradesCount.setText("Grades Count");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(lblGradesCount, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(txtGradesCount, gridBagConstraints);

        okBtn.setText("OK");
        okBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(okBtn, gridBagConstraints);

        cancelBtn.setText("Cancel");
        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(cancelBtn, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void okBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okBtnActionPerformed
        acceptChanges();
    }//GEN-LAST:event_okBtnActionPerformed

    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBtnActionPerformed
        cancelChanges();
    }//GEN-LAST:event_cancelBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelBtn;
    private javax.swing.JLabel labelCode;
    private javax.swing.JLabel labelName;
    private javax.swing.JLabel lblCapacity;
    private javax.swing.JLabel lblGradesCount;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JButton okBtn;
    private javax.swing.JTextField txtCapacity;
    private javax.swing.JTextField txtCode;
    private javax.swing.JTextField txtGradesCount;
    private javax.swing.JTextField txtName;
    // End of variables declaration//GEN-END:variables
}
