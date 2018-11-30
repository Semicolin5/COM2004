package src.view;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import src.objects.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;

import src.controller.Controller;

/**
 * ModulePick.java
 * Only accessible for Registrars (privilege level 3)
 * Extension of Form, creates a functional GUI form which allows Registrar to assign/unassign modules
 * to or from a student's current period of study.  * Assigning consists of adding a row in the Grades
 * table, unassigning means deleting a row from the Grades table.
 */
public class ModulePick extends Form {
    private JPanel panel1;
    private JList studentList;
    private String studentsLevel;
    private JLabel studentName;
    private JButton assignModuleButton;
    private JButton unassignModuleButton;
    private JLabel totalCredits;
    private JButton backButton;
    private JTable choiceTable;
    private JTable chosenTable;
    private DefaultListModel<String> studentModel;
    private DefaultTableModel choiceModel;
    private DefaultTableModel chosenModel;
    private String periodOfStudyLabel;
    private JLabel studentLevel;

    private List<Student> students = Controller.getStudents();

    /**
     * Set default JFrame sizes & add Event Listener
     *
     * @param frame - JFrame with properties set in the GUIFrame class.
     */
    public ModulePick(GUIFrame frame) {
        super(frame);

        setJPanel(panel1);
        studentModel = new DefaultListModel<>();
        choiceModel = new DefaultTableModel();
        chosenModel = new DefaultTableModel();
        frame.setTitle("Manage Modules Screen");

        studentList.setLayoutOrientation(JList.VERTICAL);
        studentList.setModel(studentModel);
        studentList.setVisibleRowCount(8);

        //Set column headers
        choiceModel.addColumn("Module Code");
        choiceModel.addColumn("Module Credits");
        choiceModel.addColumn("Core Status");
        chosenModel.addColumn("Module Code");
        chosenModel.addColumn("Module Credits");
        chosenModel.addColumn("Core Status");

        choiceTable.setModel(choiceModel);
        chosenTable.setModel(chosenModel);

        assignModuleButton.addActionListener(new AssignModuleHandler());
        unassignModuleButton.addActionListener(new UnassignModuleHandler());
        backButton.addActionListener(new backHandler());

        //Loads all the students (Just IDs) into the studentList
        for (Student student : students) {
            studentModel.addElement(student.getLogin());
        }

        /*
        When a student is selected in the studentList, all modules that student is eligible at their current
        level of study are loaded into the choiceTable JTable, and all of the modules the student is currently
        assigned are loaded into the chosenTable JTable.
         */
        studentList.addListSelectionListener(evt -> {
            int studID = Integer.parseInt(studentList.getSelectedValue().toString());
            PeriodOfStudy pos;

            if (!evt.getValueIsAdjusting()) {
                //Clear JTables
                choiceModel.setRowCount(0);
                chosenModel.setRowCount(0);

                ArrayList<String> modChoices = new ArrayList<>();
                ArrayList<String> modAssigned = new ArrayList<>();
                //private ArrayList<String> modRemain = new ArrayList<String>();

                //Retrieves the selected student's period of study label, and level of study (which it places in a textbox).
                pos = Controller.getLatestPeriodOfStudy(studID);
                studentsLevel = pos.getLevelOfStudy();
                periodOfStudyLabel = pos.getLabel();
                studentLevel.setText(periodOfStudyLabel);

                for (Student student : Controller.getStudents()) {
                    if (student.getLogin().equals(studentList.getSelectedValue())) {
                        studentName.setText(student.getForename() + " " + student.getSurname());

                        /**Retrieves a list of all modules the selected student is eligible for and adds the details of
                         each as a row in the choice JTable.*/
                        for (ModuleDegree m : Controller.getModuleDegrees()) {
                            if (m.getDegreeCode().equals(student.getDegreeCode()) && (m.getDegreeLevel().equals(studentsLevel))) {
                                for (Module mod : Controller.getModules()) {
                                    if (mod.getCode().equals(m.getModuleCode())) {
                                        modChoices.add(mod.getCode());
                                        //if (m.isCore())
                                        //    choiceModel.addRow(new Object[]{m.getModuleCode(), mod.getCredits(), "Core"});
                                        //else
                                        //    choiceModel.addRow(new Object[]{m.getModuleCode(), mod.getCredits(), "Not Core"});
                                    }
                                }
                            }
                        }

                        /**Retrieves a list of all modules the selected student is assigned and adds the details of
                         each as a row in the chosen JTable.*/

                        for (Grade grade : Controller.getStudentsGradeAtPeriod(studID, periodOfStudyLabel)) {
                            for (ModuleDegree modDeg : Controller.getModuleDegrees()) {
                                if (grade.getModuleCode().equals(modDeg.getModuleCode())) {
                                    for (Module mod : Controller.getModules()) {
                                        if (mod.getCode().equals(modDeg.getModuleCode())) {
                                            modAssigned.add(mod.getCode());
                                            if (modDeg.isCore())
                                                chosenModel.addRow(new Object[]{mod.getCode(), mod.getCredits(), "Core"});
                                            else
                                                chosenModel.addRow(new Object[]{mod.getCode(), mod.getCredits(), "Not Core"});
                                        }
                                    }
                                }
                            }
                        }

                        modChoices.removeAll(modAssigned);
                        for (String modChoice : modChoices) {
                            for (ModuleDegree modDeg : Controller.getModuleDegrees()) {
                                if (modChoice.equals(modDeg.getModuleCode())) {
                                    for (Module mod : Controller.getModules()) {
                                        if (mod.getCode().equals(modChoice)) {
                                            if (modDeg.isCore())
                                                choiceModel.addRow(new Object[]{modChoice, mod.getCredits(), "Core"});
                                            else
                                                choiceModel.addRow(new Object[]{modChoice, mod.getCredits(), "Not Core"});
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                calculateCredits();
            }
        });
    }

    /**
     * Class which calculate the total credits of the modules a student has assigned.
     */
    private void calculateCredits() {
        int total = 0;
        for (int i = 0; i < chosenModel.getRowCount(); i++) {
            int credits = Integer.parseInt(chosenModel.getValueAt(i, 1).toString());
            total += credits;
        }
        totalCredits.setText(String.valueOf(total));
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
        panel1.setLayout(new GridLayoutManager(8, 6, new Insets(0, 0, 0, 0), -1, -1));
        final JScrollPane scrollPane1 = new JScrollPane();
        panel1.add(scrollPane1, new GridConstraints(1, 0, 4, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        studentList = new JList();
        studentList.setSelectionMode(0);
        scrollPane1.setViewportView(studentList);
        final JScrollPane scrollPane2 = new JScrollPane();
        panel1.add(scrollPane2, new GridConstraints(1, 4, 4, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        choiceTable = new JTable();
        scrollPane2.setViewportView(choiceTable);
        final JScrollPane scrollPane3 = new JScrollPane();
        panel1.add(scrollPane3, new GridConstraints(1, 5, 4, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        chosenTable = new JTable();
        scrollPane3.setViewportView(chosenTable);
        final JLabel label1 = new JLabel();
        label1.setText("List of Students");
        panel1.add(label1, new GridConstraints(0, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Module Choices");
        panel1.add(label2, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Selected Modules");
        panel1.add(label3, new GridConstraints(0, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        assignModuleButton = new JButton();
        assignModuleButton.setText("Assign Module to Student");
        panel1.add(assignModuleButton, new GridConstraints(5, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        unassignModuleButton = new JButton();
        unassignModuleButton.setText("Unassign Module from Student");
        panel1.add(unassignModuleButton, new GridConstraints(5, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("Total Credits Selected:");
        panel1.add(label4, new GridConstraints(6, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        totalCredits = new JLabel();
        totalCredits.setText("");
        panel1.add(totalCredits, new GridConstraints(6, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        backButton = new JButton();
        backButton.setText("Back");
        panel1.add(backButton, new GridConstraints(7, 0, 1, 6, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setText("Student Name");
        panel1.add(label5, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label6 = new JLabel();
        label6.setText("Student Degree Level");
        panel1.add(label6, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        studentName = new JLabel();
        studentName.setBackground(new Color(-4473925));
        studentName.setText("");
        panel1.add(studentName, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        studentLevel = new JLabel();
        studentLevel.setBackground(new Color(-4473925));
        studentLevel.setText("");
        panel1.add(studentLevel, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }

    /**
     * ActionListener which assigns the module selected in the choiceTable JTable to the selected student.
     */
    public class AssignModuleHandler implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            if (choiceTable.getSelectedRow() != -1) {
                int rowNumber = choiceTable.getSelectedRow();
                String code = choiceTable.getValueAt(rowNumber, 0).toString();
                String cred = choiceTable.getValueAt(rowNumber, 1).toString();
                String coreStatus = choiceTable.getValueAt(rowNumber, 2).toString();
                Controller.saveBlankGrades(studentList.getSelectedValue().toString(), code, periodOfStudyLabel);
                chosenModel.addRow(new Object[]{code, cred, coreStatus});
            }
            calculateCredits();
        }
    }

    /**
     * ActionListener which unassigns the module selected in the choiceTable JTable to the selected student.
     */
    public class UnassignModuleHandler implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            if (chosenTable.getSelectedRow() != -1) {
                int rowNumber = chosenTable.getSelectedRow();
                String code = chosenTable.getValueAt(rowNumber, 0).toString();
                String cred = chosenTable.getValueAt(rowNumber, 1).toString();
                String coreStatus = chosenTable.getValueAt(rowNumber, 2).toString();

                //We shouldn't be able to unassign core modules
                if (coreStatus.equals("Core")) {
                    JOptionPane.showMessageDialog(getFrame(), "Cannot unassign core modules.");
                } else {
                    Controller.removeGrades(Integer.parseInt(studentList.getSelectedValue().toString()), code, periodOfStudyLabel);
                    chosenModel.removeRow(rowNumber);

                }

            }
            calculateCredits();
        }
    }

    /**
     * ActionListener which takes the user back to the Welcome form.
     */
    public class backHandler implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            changeJPanel(new Welcome(getFrame()).getJPanel());
        }
    }
}
