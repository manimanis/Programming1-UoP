
package org.manisoft.dialogs;

import java.awt.Frame;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author manianis
 * @param <T>
 */
public class DialogBase<T extends Object> extends JDialog {

    protected T data;
    protected int dialogResult = JOptionPane.CANCEL_OPTION;
    protected OperationType opType = OperationType.ADD;
    
    /**
     * Creates new form DialogBase
     * @param parent
     * @param opType
     */
    public DialogBase(Frame parent, OperationType opType) {
        super(parent, true);
        this.opType = opType;
        initComponents();
        setLocationRelativeTo(parent);
    }

    /**
     * Get the object displayed by this dialog.
     * @return the object
     */
    public T getData() {
        return data;
    }

    /**
     * Set the object displayed by this dialog.
     * @param data The data
     */
    public void setData(T data) {
        this.data = data;
        updateInterface();
    }

    /**
     * Get the dialog result.
     * @return JOptionPane.CANCEL_OPTION or JOptionPane.OK_OPTION
     */
    public int getDialogResult() {
        return dialogResult;
    }
    
    /**
     * Update the dialog component from the data variable.
     */
    public void updateInterface() {
        
    }
    
    /**
     * Update the data variable from the dialog content.
     */
    public void updateData() {
        
    }
    
    /**
     * Verifies if the dialog's components are valid.
     * @return true if the dialog content is valid
     */
    public boolean isValidData() {
        return true;
    }
    
    /**
     * Invoked when clicking Ok button.
     */
    public void acceptChanges() {
        dialogResult = JOptionPane.CANCEL_OPTION;
        if (isValidData()) {
            dialogResult = JOptionPane.OK_OPTION;
            updateData();
            setVisible(false);
        }
    }
    
    /**
     * Invoked when clicking Cancel button.
     */
    public void cancelChanges() {
        dialogResult = JOptionPane.CANCEL_OPTION;
        setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
