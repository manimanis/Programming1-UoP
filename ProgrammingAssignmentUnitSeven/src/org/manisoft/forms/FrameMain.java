package org.manisoft.forms;

import java.awt.BorderLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import org.manisoft.containers.CourseList;
import org.manisoft.containers.EnrolledCoursesList;
import org.manisoft.containers.StudentList;
import org.manisoft.entities.Course;
import org.manisoft.entities.CourseManagement;
import org.manisoft.entities.Student;
import org.manisoft.panels.PanelCourses;
import org.manisoft.panels.PanelEnrolledCourses;
import org.manisoft.panels.PanelGrades;
import org.manisoft.panels.PanelStudents;
import org.manisoft.panels.PanelWelcome;

/**
 *
 * @author manianis
 */
public final class FrameMain extends javax.swing.JFrame {

    private String filePath;
    private StudentList students = new StudentList();
    private CourseList courses = new CourseList();
    private EnrolledCoursesList enrollment = new EnrolledCoursesList();

    private static final int PANEL_WELCOME = 0;
    private static final int PANEL_STUDENTS = 1;
    private static final int PANEL_COURSES = 2;
    private static final int PANEL_ENROLLMENTS = 3;
    private static final int PANEL_GRADES = 4;

    private JPanel currPanel = null;
    private final JPanel[] winPanels;

    /**
     * Creates new form FrameMain
     *
     * @param filePath
     */
    public FrameMain(String filePath) {
        initComponents();

        this.filePath = filePath;
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
        winPanels = new JPanel[5];
        startWelcomePage();
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
        welcomeMenu = new javax.swing.JMenuItem();
        studentsMenu = new javax.swing.JMenuItem();
        coursesMenu = new javax.swing.JMenuItem();
        studentsEnrollMenu = new javax.swing.JMenuItem();
        gradesMenu = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Students Management");

        fileMenu.setText("File");

        exitMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.ALT_DOWN_MASK));
        exitMenu.setText("Exit");
        exitMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenu);

        menuBar.add(fileMenu);

        managementMenu.setText("Management");
        managementMenu.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                managementMenuStateChanged(evt);
            }
        });

        welcomeMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.ALT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        welcomeMenu.setText("Welcome Page");
        welcomeMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                welcomeMenuActionPerformed(evt);
            }
        });
        managementMenu.add(welcomeMenu);

        studentsMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        studentsMenu.setText("Students");
        studentsMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                studentsMenuActionPerformed(evt);
            }
        });
        managementMenu.add(studentsMenu);

        coursesMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        coursesMenu.setText("Courses");
        coursesMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                coursesMenuActionPerformed(evt);
            }
        });
        managementMenu.add(coursesMenu);

        studentsEnrollMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.ALT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        studentsEnrollMenu.setText("Students Enrollments");
        studentsEnrollMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                studentsEnrollMenuActionPerformed(evt);
            }
        });
        managementMenu.add(studentsEnrollMenu);

        gradesMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.ALT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
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

    /**
     * Load students, courses and enrollments data from a file.
     */
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
            Logger.getLogger(CourseManagement.class.getName())
                    .log(Level.SEVERE, null, ex);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(CourseManagement.class.getName())
                    .log(Level.SEVERE, null, ex);
        } finally {
            try {
                ois.close();
            } catch (IOException ex) {
                Logger.getLogger(CourseManagement.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Saves students, courses and enrollments data to a file.
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
            Logger.getLogger(StudentList.class.getName())
                    .log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(StudentList.class.getName())
                    .log(Level.SEVERE, null, ex);
        } finally {
            try {
                oos.close();
            } catch (IOException ex) {
                Logger.getLogger(StudentList.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Display the panel and make it the current panel.
     * @param panel 
     */
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

    /**
     * Display welcome panel.
     */
    public void startWelcomePage() {
        if (winPanels[PANEL_WELCOME] == null) {
            winPanels[PANEL_WELCOME] = new PanelWelcome();
        }
        displayPanel(winPanels[PANEL_WELCOME]);
    }

    /**
     * Display students management panel.
     */
    public void startStudentsManagement() {
        if (winPanels[PANEL_STUDENTS] == null) {
            winPanels[PANEL_STUDENTS] = new PanelStudents();
        }
        displayPanel(winPanels[PANEL_STUDENTS]);
        ((PanelStudents) winPanels[PANEL_STUDENTS])
                .setStudentList(students);
    }

    /**
     * Display courses management panel.
     */
    public void startCoursesManagement() {
        if (winPanels[PANEL_COURSES] == null) {
            winPanels[PANEL_COURSES] = new PanelCourses();
        }
        displayPanel(winPanels[PANEL_COURSES]);
        ((PanelCourses) winPanels[PANEL_COURSES])
                .setCourseList(courses);
    }

    /**
     * Display enrollments management panel.
     */
    public void startEnrollmentsManagement() {
        if (winPanels[PANEL_ENROLLMENTS] == null) {
            winPanels[PANEL_ENROLLMENTS] = new PanelEnrolledCourses();
        }
        displayPanel(winPanels[PANEL_ENROLLMENTS]);
        ((PanelEnrolledCourses) winPanels[PANEL_ENROLLMENTS])
                .setCourseList(courses);
        ((PanelEnrolledCourses) winPanels[PANEL_ENROLLMENTS])
                .setStudentList(students);
        ((PanelEnrolledCourses) winPanels[PANEL_ENROLLMENTS])
                .setEnrolledCoursesList(enrollment);
    }

    /**
     * Display grades management panel.
     */
    public void startGradesManagement() {
        if (winPanels[PANEL_GRADES] == null) {
            winPanels[PANEL_GRADES] = new PanelGrades();
        }
        displayPanel(winPanels[PANEL_GRADES]);
        ((PanelGrades) winPanels[PANEL_GRADES])
                .setCourseList(courses);
        ((PanelGrades) winPanels[PANEL_GRADES])
                .setStudentList(students);
        ((PanelGrades) winPanels[PANEL_GRADES])
                .setEnrolledCoursesList(enrollment);
    }

    private void exitMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitMenuActionPerformed

    private void studentsMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_studentsMenuActionPerformed
        startStudentsManagement();
    }//GEN-LAST:event_studentsMenuActionPerformed

    private void coursesMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_coursesMenuActionPerformed
        startCoursesManagement();
    }//GEN-LAST:event_coursesMenuActionPerformed

    private void studentsEnrollMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_studentsEnrollMenuActionPerformed
        startEnrollmentsManagement();
    }//GEN-LAST:event_studentsEnrollMenuActionPerformed

    private void gradesMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gradesMenuActionPerformed
        startGradesManagement();
    }//GEN-LAST:event_gradesMenuActionPerformed

    private void welcomeMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_welcomeMenuActionPerformed
        startWelcomePage();
    }//GEN-LAST:event_welcomeMenuActionPerformed

    private void managementMenuStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_managementMenuStateChanged
        for (int i = 0; i < managementMenu.getItemCount(); i++) {
            JMenuItem menuItem = managementMenu.getItem(i);
            menuItem.setVisible(currPanel != winPanels[i]);
        }
    }//GEN-LAST:event_managementMenuStateChanged

    
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
            frameMain = new FrameMain("courses.obj");
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
    private javax.swing.JMenuItem welcomeMenu;
    // End of variables declaration//GEN-END:variables
}
