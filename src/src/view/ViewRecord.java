package src.view;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import src.controller.Controller;
import src.controller.Main;
import src.objects.Grade;
import src.objects.PeriodOfStudy;
import src.objects.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ViewRecords.java
 * Only accessible to Students and Teachers
 */
public class ViewRecord extends Form {

    private JButton backButton;
    private JButton loadRecordButton;
    private JTable displayOutcome;
    private JList periodList;
    private JPanel panel1;
    private JList selectStudent;
    private JButton loadStudentButton;
    private DefaultTableModel outcomeModel;
    private DefaultListModel<String> periodListModel;
    private DefaultListModel<String> studentListModel;
    private int username; // int for the current user record being viewed
    private boolean isTeacher; //TODO possibly delete this

    /**
     * Constructor sets up an empty JTable, and sets up a JList containing the periods of study, and levels for the
     * student.
     * The privilege is checked to determine if it is a student or a teacher viewing the record. If it is a teacher,
     * then another JList is loaded containing all the students. The teacher can load this student and view their progress.
     * If a student is looking at their record, this list is not loaded, and they can only see their own record.
     * @param frame - JFrame with properties defined in the GUIFrame class.
     */
    public ViewRecord(GUIFrame frame) {
        super(frame);

        // setting up JLists and Tables depending on whether being viewed by teacher or student
        isTeacher = false; // if the teacher is viewing the records, they can see all students records
        periodListModel = new DefaultListModel<>();
        // displays the page differently depending if the
        if (Main.getPriv() == 2) { // running for a teacher
            isTeacher = true;
            // if the user is a teacher, load the JList enabling different students to be selected
            studentListModel = new DefaultListModel<>();
            for (Student s : Controller.getStudents()) {
                System.out.println("student: " + s.toString());
                studentListModel.addElement(s.getLogin());
            }
            selectStudent.setModel(studentListModel);
        } else if (Main.getPriv() == 1) { // running for a student
            username = Main.getLoginID();
            // filling JList
            for (PeriodOfStudy p : Controller.getPeriodsOfStudyForStudent(username)) {
                periodListModel.addElement(p.getLabel()); // selects students periods of study
            }
        }

        // setup the backbutton
        setBackButton(backButton);
        setBackButtonPanel(new Welcome(getFrame()).getJPanel());
        setJPanel(panel1);
        frame.setTitle("View Record");


        // setting up the columns in the table
        outcomeModel = new DefaultTableModel();
        outcomeModel.addColumn("Module");
        outcomeModel.addColumn("Initial Percent Achieved");
        outcomeModel.addColumn("Resit Percent Achieved");
        periodList.setLayoutOrientation(JList.VERTICAL);
        periodList.setModel(periodListModel);
        periodList.setVisibleRowCount(10);
        displayOutcome.setModel(outcomeModel);

        // setting up loadRecordButton and loadStudentButton
        loadRecordButton.addActionListener(new LoadRecordHandler());
        loadStudentButton.addActionListener(new LoadStudentHandler());
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(13, 2, new Insets(0, 0, 0, 0), -1, -1));
        final JScrollPane scrollPane1 = new JScrollPane();
        panel1.add(scrollPane1, new GridConstraints(0, 0, 6, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        periodList = new JList();
        final DefaultListModel defaultListModel1 = new DefaultListModel();
        periodList.setModel(defaultListModel1);
        scrollPane1.setViewportView(periodList);
        final JScrollPane scrollPane2 = new JScrollPane();
        panel1.add(scrollPane2, new GridConstraints(0, 1, 6, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        displayOutcome = new JTable();
        scrollPane2.setViewportView(displayOutcome);
        final JScrollPane scrollPane3 = new JScrollPane();
        panel1.add(scrollPane3, new GridConstraints(6, 0, 6, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        selectStudent = new JList();
        scrollPane3.setViewportView(selectStudent);
        backButton = new JButton();
        backButton.setText("Back");
        panel1.add(backButton, new GridConstraints(11, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        loadRecordButton = new JButton();
        loadRecordButton.setText("Load Record");
        panel1.add(loadRecordButton, new GridConstraints(7, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        loadStudentButton = new JButton();
        loadStudentButton.setText("Load Student");
        panel1.add(loadStudentButton, new GridConstraints(9, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel1.add(spacer1, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        panel1.add(spacer2, new GridConstraints(8, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        panel1.add(spacer3, new GridConstraints(10, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer4 = new Spacer();
        panel1.add(spacer4, new GridConstraints(12, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }


    /**
     * LoadRecordHandler loads a students progress in a given period of study onto a JTable
     * possible that username hasn't been defined (if the teacher is viewing and hasn't yet loaded
     * a student.) //TODO possibly add check and do nothing rather than have unhandled nullpointerexception
     */
    private class LoadRecordHandler implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            outcomeModel.setRowCount(0); // resets the table
            String periodOfStudyLabel = periodList.getSelectedValue().toString(); // finds the period of study label
            for (Grade g : Controller.getStudentsGradeAtPeriod(username, periodOfStudyLabel)) {
                System.out.println("resit percent: " + g.getResitPercent());
                //TODO just need to check that resit isn't displayed as 0.00
                outcomeModel.addRow(new Object[]{g.getModuleCode(), g.getInitialPercent(), g.getResitPercent()});
            }
        }
    }

    /**
     * LoadStudentHandler targets a student. Only teachers will be able to call upon this method.
     * */
    private class LoadStudentHandler implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            if (isTeacher)
                System.out.println(selectStudent.getSelectedValue());
                periodListModel.removeAllElements(); // resets the JList
                username = Integer.parseInt((String) selectStudent.getSelectedValue()); // targeted student's login code
                System.out.println("username: " + username);
                // filling JList
                for (PeriodOfStudy p : Controller.getPeriodsOfStudyForStudent(username)) {
                    periodListModel.addElement(p.getLabel()); // selects students periods of study
                    System.out.println(p.toString());
                }
                periodList.setModel(periodListModel);
        }
    }

}
