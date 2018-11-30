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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * ViewRecords.java
 * Only accessible to Students and Teachers
 */
public class ViewRecord extends Form {

    private JButton backButton;
    private JButton loadRecordButton;
    private JTable displayOutcome;
    private JPanel panel1;
    private JList selectStudent;
    private JButton loadStudentButton;
    private DefaultTableModel outcomeModel;
    private DefaultListModel<String> studentListModel;
    private int username; // int for the current user record being viewed
    private PeriodOfStudy latestPOS;
    private JScrollPane studentScrollPane;
    private JComboBox periodComboBox;
    private JTextField titleField;
    private JTextField forenameField;
    private JTextField surnameField;
    private JTextField tutorField;
    private JTextField emailField;
    private JTextField degreeField;
    private JTextField idField;
    private JTextField degreeLevelField;
    private JTextField periodStartField;
    private JTextField periodEndField;
    private JButton progressStudentButton;

    /**
     * Constructor sets up an empty JTable, and sets up a JList containing the periods of study, and levels for the
     * student.
     * The privilege is checked to determine if it is a student or a teacher viewing the record. If it is a teacher,
     * then another JList is loaded containing all the students. The teacher can load this student and view their progress.
     * If a student is looking at their record, this list is not loaded, and they can only see their own record.
     *
     * @param frame - JFrame with properties defined in the GUIFrame class.
     */
    public ViewRecord(GUIFrame frame) {
        super(frame);

        backButton.addActionListener(new BackButtonHandler());
        progressStudentButton.addActionListener(new ProgressHandler());

        // setting up JLists and Tables depending on whether being viewed by teacher or student
        // displays the page differently depending if the
        if (Main.getPriv() == 2) { // running for a teacher
            // if the user is a teacher, load the JList enabling different students to be selected
            studentListModel = new DefaultListModel<>();
            for (Student s : Controller.getStudents()) {
                studentListModel.addElement(s.getLogin());
            }
            selectStudent.setModel(studentListModel);
        } else if (Main.getPriv() == 1) { // running for a student
            //Remove teacher specific form components
            studentScrollPane.setVisible(false);
            loadStudentButton.setVisible(false);

            username = Main.getLoginID();
            setupPeriodCombo(username);
            addStudentInfo(username);
        }

        setJPanel(panel1);
        frame.setTitle("View Record");

        // setting up the columns in the table
        outcomeModel = new DefaultTableModel();
        outcomeModel.addColumn("Module");
        outcomeModel.addColumn("Initial Percent Achieved");
        outcomeModel.addColumn("Resit Percent Achieved");
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
        panel1.setLayout(new GridLayoutManager(16, 5, new Insets(0, 0, 0, 0), -1, -1));
        final JScrollPane scrollPane1 = new JScrollPane();
        panel1.add(scrollPane1, new GridConstraints(0, 2, 7, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        displayOutcome = new JTable();
        scrollPane1.setViewportView(displayOutcome);
        studentScrollPane = new JScrollPane();
        panel1.add(studentScrollPane, new GridConstraints(7, 0, 8, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        selectStudent = new JList();
        studentScrollPane.setViewportView(selectStudent);
        backButton = new JButton();
        backButton.setText("Back");
        panel1.add(backButton, new GridConstraints(14, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        loadRecordButton = new JButton();
        loadRecordButton.setText("Load Record");
        panel1.add(loadRecordButton, new GridConstraints(8, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        loadStudentButton = new JButton();
        loadStudentButton.setText("Load Student");
        panel1.add(loadStudentButton, new GridConstraints(10, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel1.add(spacer1, new GridConstraints(7, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        panel1.add(spacer2, new GridConstraints(9, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        panel1.add(spacer3, new GridConstraints(11, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer4 = new Spacer();
        panel1.add(spacer4, new GridConstraints(15, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        periodComboBox = new JComboBox();
        panel1.add(periodComboBox, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Forename");
        panel1.add(label1, new GridConstraints(2, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Surname");
        panel1.add(label2, new GridConstraints(3, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Personal Tutor");
        panel1.add(label3, new GridConstraints(4, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(113, 58), null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("Degree Code");
        panel1.add(label4, new GridConstraints(6, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setText("Email");
        panel1.add(label5, new GridConstraints(5, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label6 = new JLabel();
        label6.setText("Title");
        panel1.add(label6, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        titleField = new JTextField();
        titleField.setEditable(false);
        titleField.setEnabled(true);
        panel1.add(titleField, new GridConstraints(1, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        forenameField = new JTextField();
        forenameField.setEditable(false);
        panel1.add(forenameField, new GridConstraints(2, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        surnameField = new JTextField();
        surnameField.setEditable(false);
        panel1.add(surnameField, new GridConstraints(3, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        tutorField = new JTextField();
        tutorField.setEditable(false);
        panel1.add(tutorField, new GridConstraints(4, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 58), null, 0, false));
        emailField = new JTextField();
        emailField.setEditable(false);
        panel1.add(emailField, new GridConstraints(5, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        degreeField = new JTextField();
        degreeField.setEditable(false);
        panel1.add(degreeField, new GridConstraints(6, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label7 = new JLabel();
        label7.setText("User ID");
        panel1.add(label7, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        idField = new JTextField();
        idField.setEditable(false);
        panel1.add(idField, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label8 = new JLabel();
        label8.setText("Level of Study");
        panel1.add(label8, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        degreeLevelField = new JTextField();
        degreeLevelField.setEditable(false);
        panel1.add(degreeLevelField, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label9 = new JLabel();
        label9.setText("Period Start Date");
        panel1.add(label9, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label10 = new JLabel();
        label10.setText("Period End Date");
        panel1.add(label10, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(123, 58), null, 0, false));
        periodStartField = new JTextField();
        periodStartField.setEditable(false);
        panel1.add(periodStartField, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        periodEndField = new JTextField();
        periodEndField.setEditable(false);
        panel1.add(periodEndField, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 58), null, 0, false));
        progressStudentButton = new JButton();
        progressStudentButton.setText("Progress Student");
        panel1.add(progressStudentButton, new GridConstraints(12, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer5 = new Spacer();
        panel1.add(spacer5, new GridConstraints(13, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }

    /**
     * LoadRecordHandler loads a students progress in a given period of study onto a JTable
     */
    private class LoadRecordHandler implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            //If a user has been selected
            if (username != 0) {
                outcomeModel.setRowCount(0); // resets the table
                String periodOfStudyLabel = periodComboBox.getSelectedItem().toString(); // finds the period of study label
                for (Grade g : Controller.getStudentsGradeAtPeriod(username, periodOfStudyLabel)) {
                    //Set appropriate message if grade is null in database
                    String initialGrade = "Not taken";
                    if (g.getInitialPercent() != -1) {
                        initialGrade = String.valueOf(g.getInitialPercent());
                    }

                    String resitGrade = "Not taken";
                    if (g.getResitPercent() != -1) {
                        resitGrade = String.valueOf(g.getResitPercent());
                    }

                    outcomeModel.addRow(new Object[]{g.getModuleCode(), initialGrade, resitGrade});
                }

                PeriodOfStudy periodOfStudy = Controller.getPeriodsOfStudyForStudent(username, periodOfStudyLabel);
                //Fill period of study info fields
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                degreeLevelField.setText(periodOfStudy.getLevelOfStudy());
                periodStartField.setText(dateFormat.format(periodOfStudy.getStartDate()));
                periodEndField.setText(dateFormat.format(periodOfStudy.getEndDate()));
            }
        }
    }

    /**
     * LoadStudentHandler targets a student. Only teachers will be able to call upon this method.
     */
    private class LoadStudentHandler implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            if (selectStudent.getSelectedValue() != null) {


                username = Integer.parseInt((String) selectStudent.getSelectedValue()); // targeted student's login code
                latestPOS = Controller.getLatestPeriodOfStudy(username);
                setupPeriodCombo(username);

                //Clear the results table
                outcomeModel.setRowCount(0);

                //Clear period of study information
                degreeLevelField.setText("");
                periodStartField.setText("");
                periodEndField.setText("");

                addStudentInfo(username);
            }
        }
    }

    /**
     * Button
     */
    private class ProgressHandler implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            int expectedTotalCredits; // the expected credits a student should be taking
            float min; // the minimum pass grade on a module
            if (latestPOS.getLevelOfStudy() != "4") {
                expectedTotalCredits = 120;
                min = 40;
            } else { // if student isn't doing a masters
                expectedTotalCredits = 180;
                min = 50;
            }
            System.out.println("credits that they take: " + Controller.latestTotalCredits(username) + ", expected is: " + expectedTotalCredits);
            if (expectedTotalCredits == Controller.latestTotalCredits(username)) {
                List<Grade> gs = Controller.getStudentsGradeAtPeriod(username, latestPOS.getLabel());
                float sumOfGrades = 0; // add the best score from each module
                List<Grade> failedModules = new ArrayList<Grade>();
                /**
                 * For each grade in the latestPOS taken, calculate if the student passe
                 e */
                System.out.println("Conceded Pass Check");
                for (Grade g : gs) {
                    sumOfGrades = sumOfGrades + (Controller.getMaximumScore(g, min) * (Controller.getGradeWeighting(g)));

                    // this calculates number of fa
                    if ((Controller.getMaximumScore(g, min) < min)) {
                        failedModules.add(g);
                    }
                }
                float average = sumOfGrades / expectedTotalCredits;

                // adds average to the period_of_study
                Controller.updatePeriodOfStudy(username, latestPOS.getLabel(), average);

                System.out.println("average score from all modules: " + average);

                System.out.println("\n~~controlflow~~");
                System.out.println("average: " + average + ", failedModules size: " + failedModules.size());

                // control flow to work out students progression to next period of study
                if (average >= min && (failedModules.size() == 0)) {
                    // pass normally
                    System.out.println("Colin's");
                } else if (average < min || (failedModules.size() > 1)) {
                    // check to see if they have failed the year
                    if (failedModules.get(0).getRepeated()) {
                        // cannot resit if they have already repeated this level
                    } else {
                        System.out.println("progresing student to repeat year");
                        char newLabel = latestPOS.getLabel().charAt(0);
                        newLabel++;
                        // TODO hardcoding date
                        String initDate = latestPOS.getStartDate().toString();
                        String endDate = latestPOS.getEndDate().toString();
                        Controller.addPeriodOfStudy(username, String.valueOf(newLabel), initDate, endDate, latestPOS.getLevelOfStudy());
                    }
                    System.out.println("User has failed this year system finds out what to do."); //
                    failStudent();
                } else if (failedModules.size() == 1) {
                    System.out.println("Conceded Pass Check");
                    if (conceededPassCheck(failedModules.get(0), min)) {
                        System.out.println("collin's function");
                        // collins functions
                    }
                }

            } else {
                // TODO make a popup
                System.out.println("Error, User Doesn't Take Enough Modules");
            }
        }
    }

    private class BackButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            changeJPanel(new Welcome(getFrame()).getJPanel());
        }
    }

    private void setupPeriodCombo(int loginID) {
        periodComboBox.removeAllItems();
        for (PeriodOfStudy pos : Controller.getPeriodsOfStudyForStudent(loginID)) {
            periodComboBox.addItem(pos.getLabel());
        }
    }

    /**
     * FailStudent method handles GUI when the student fails.
     * */
    private void failStudent() {
        List<Grade> gs = Controller.getStudentsGradeAtPeriod(username, latestPOS.getLabel());
        if (gs.get(0).getRepeated()) {
            System.out.println("FAIL");
        } else {
            if (latestPOS.getLevelOfStudy().equals(4))
                System.out.println("Graduate with equivalent bachelors with credits already obtained");
                // TODO graduation method.
            else {
                JOptionPane.showMessageDialog(getFrame(), "Fail student");
            }
        }
    }

    /**
     * conceededPassCheck checks whether a student who failed one modules is eligible for a conceded pass,
     * or should be failed.
     *
     * @param failedModule
     * @param min
     */
    private boolean conceededPassCheck(Grade failedModule, float min) {
        float maxScore = Controller.getMaximumScore(failedModule, min);
        boolean concededPass = false;
        if (min == 40 && (maxScore >= 36)) {
            concededPass = true;
        }
        if (min == 50 && (maxScore >= 45)) {
            concededPass = true;
        }
        return concededPass;
    }

    private void addStudentInfo(int loginID) {
        Student student = Controller.getStudent(loginID);

        idField.setText(student.getLogin());
        titleField.setText(student.getTitle());
        forenameField.setText(student.getForename());
        surnameField.setText(student.getSurname());
        tutorField.setText(student.getPersonalTutor());
        emailField.setText(student.getEmail());
        degreeField.setText(student.getDegreeCode());
    }

}
