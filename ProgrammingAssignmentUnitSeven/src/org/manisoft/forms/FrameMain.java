package org.manisoft.forms;

import org.manisoft.panels.PanelStudents;
import org.manisoft.panels.PanelEnrolledCourses;
import org.manisoft.panels.PanelCourses;
import java.awt.BorderLayout;
import java.awt.Container;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import org.manisoft.containers.CourseList;
import org.manisoft.containers.EnrolledCoursesList;
import org.manisoft.containers.StudentList;
import org.manisoft.entities.Course;
import org.manisoft.entities.CourseManagement;
import org.manisoft.entities.Student;
import org.manisoft.panels.PanelGrades;

/**
 *
 * @author manianis
 */
public class FrameMain extends javax.swing.JFrame {

    private String filePath = "courses.obj";
    private StudentList students = new StudentList();
    private CourseList courses = new CourseList();
    private EnrolledCoursesList enrollment = new EnrolledCoursesList();

    private JPanel currPanel = null;
    private PanelStudents panelStudents;
    private PanelCourses panelCourses;
    private PanelEnrolledCourses panelEnrolledCourses;
    private PanelGrades panelGrades;

    /**
     * Creates new form FrameMain
     */
    public FrameMain() {
        initComponents();

        loadData();
        if (students.isEmpty()) {
            Student student1 = new Student(students.genStudentID(), "Amine");
            Student student2 = new Student(students.genStudentID(), "Zaher");
            Course course1 = new Course("CS1101", "Programming Fundamentals");
            Course course2 = new Course("CS1102", "Programming One");
            students.add(student1);
            students.add(student2);
            courses.add(course1);
            courses.add(course2);
            enrollment.enrollToCourse(student1, course1);
            enrollment.enrollToCourse(student2, course2);
            saveData();
        }

        Container pane = getContentPane();
        pane.setLayout(new BorderLayout());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        exitMenu = new javax.swing.JMenuItem();
        managementMenu = new javax.swing.JMenu();
        studentsMenu = new javax.swing.JMenuItem();
        coursesMenu = new javax.swing.JMenuItem();
        studentsEnrollMenu = new javax.swing.JMenuItem();
        gradesMenu = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Students Management");

        fileMenu.setText("File");

        exitMenu.setText("Exit");
        exitMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenu);

        menuBar.add(fileMenu);

        managementMenu.setText("Management");

        studentsMenu.setText("Students");
        studentsMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                studentsMenuActionPerformed(evt);
            }
        });
        managementMenu.add(studentsMenu);

        coursesMenu.setText("Courses");
        coursesMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                coursesMenuActionPerformed(evt);
            }
        });
        managementMenu.add(coursesMenu);

        studentsEnrollMenu.setText("Students Enrollments");
        studentsEnrollMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                studentsEnrollMenuActionPerformed(evt);
            }
        });
        managementMenu.add(studentsEnrollMenu);

        gradesMenu.setText("Grades");
        gradesMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gradesMenuActionPerformed(evt);
            }
        });
        managementMenu.add(gradesMenu);

        menuBar.add(managementMenu);

        setJMenuBar(menuBar);

        setSize(new java.awt.Dimension(577, 443));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public void loadData() {
        ObjectInputStream ois = null;
        File fichier = new File(filePath);
        if (!fichier.exists()) {
            return;
        }
        try {
            ois = new ObjectInputStream(new FileInputStream(fichier));
            students = (StudentList) ois.readObject();
            courses = (CourseList) ois.readObject();
            enrollment = (EnrolledCoursesList) ois.readObject();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CourseManagement.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(CourseManagement.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ois.close();
            } catch (IOException ex) {
                Logger.getLogger(CourseManagement.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Saves data to a file.
     */
    public void saveData() {
        ObjectOutputStream oos = null;
        try {
            File fichier = new File(filePath);
            oos = new ObjectOutputStream(new FileOutputStream(fichier));
            oos.writeObject(students);
            oos.writeObject(courses);
            oos.writeObject(enrollment);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(StudentList.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(StudentList.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                oos.close();
            } catch (IOException ex) {
                Logger.getLogger(StudentList.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void displayPanel(JPanel panel) {
        if (panel == currPanel) {
            return;
        }
        if (currPanel != null) {
            getContentPane().remove(currPanel);
        }
        getContentPane().add(panel, BorderLayout.CENTER);
        SwingUtilities.updateComponentTreeUI(this);
        currPanel = panel;
    }

    private void exitMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuActionPerformed

    }//GEN-LAST:event_exitMenuActionPerformed

    private void studentsMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_studentsMenuActionPerformed
        if (panelStudents == null) {
            panelStudents = new PanelStudents();
        }
        displayPanel(panelStudents);
        panelStudents.setStudentList(students);
    }//GEN-LAST:event_studentsMenuActionPerformed

    private void coursesMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_coursesMenuActionPerformed
        if (panelCourses == null) {
            panelCourses = new PanelCourses();
        }
        displayPanel(panelCourses);
        panelCourses.setCourseList(courses);
    }//GEN-LAST:event_coursesMenuActionPerformed

    private void studentsEnrollMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_studentsEnrollMenuActionPerformed
        if (panelEnrolledCourses == null) {
            panelEnrolledCourses = new PanelEnrolledCourses();
        }
        displayPanel(panelEnrolledCourses);
        panelEnrolledCourses.setCourseList(courses);
        panelEnrolledCourses.setStudentList(students);
        panelEnrolledCourses.setEnrolledCoursesList(enrollment);
    }//GEN-LAST:event_studentsEnrollMenuActionPerformed

    private void gradesMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gradesMenuActionPerformed
        if (panelGrades == null) {
            panelGrades = new PanelGrades();
        }
        displayPanel(panelGrades);
        panelGrades.setCourseList(courses);
        panelGrades.setStudentList(students);
        panelGrades.setEnrolledCoursesList(enrollment);
    }//GEN-LAST:event_gradesMenuActionPerformed

    public static FrameMain frameMain;

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrameMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            frameMain = new FrameMain();
            frameMain.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem coursesMenu;
    private javax.swing.JMenuItem exitMenu;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenuItem gradesMenu;
    private javax.swing.JMenu managementMenu;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem studentsEnrollMenu;
    private javax.swing.JMenuItem studentsMenu;
    // End of variables declaration//GEN-END:variables
}
