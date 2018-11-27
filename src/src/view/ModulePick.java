package src.view;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import src.objects.Student;
import src.objects.PeriodOfStudy;
import src.objects.ModuleDegree;
import src.objects.Module;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import src.controller.Controller;

public class ModulePick extends Form {
    private JPanel panel1;
    private JList studentList;
    private JList moduleChoiceList;
    private JList chosenModuleList;
    private JLabel studentLevel;
    private JLabel studentName;
    private JButton assignModuleToStudentButton;
    private JButton unassignModuleFromStudentButton;
    private JButton submitModuleChoiceForButton;
    private JLabel totalCredits;
    private JButton backButton;
    private JTable choiceTable;
    private JTable chosenTable;
    private DefaultListModel<String> studentModel;
    private DefaultTableModel choiceModel;
    private DefaultTableModel chosenModel;
    private String periodOfStudyLabel;


    public ModulePick(GUIFrame frame) {
        super(frame);

        //Set back button
        setBackButton(backButton);
        setBackButtonPanel(new Welcome(getFrame()).getJPanel());

        setJPanel(panel1);
        studentModel = new DefaultListModel<>();
        choiceModel = new DefaultTableModel();
        chosenModel = new DefaultTableModel();
        frame.setTitle("Manage Modules Screen");

        studentList.setLayoutOrientation(JList.VERTICAL);
        studentList.setModel(studentModel);
        studentList.setVisibleRowCount(8);

        choiceModel.addColumn("Module Code");
        choiceModel.addColumn("Module Credits");
        choiceModel.addColumn("Core Status");
        chosenModel.addColumn("Module Code");
        chosenModel.addColumn("Module Credits");
        chosenModel.addColumn("Core Status");

        choiceTable.setModel(choiceModel);
        chosenTable.setModel(chosenModel);

        assignModuleToStudentButton.addActionListener(new assignModuleHandler());
        unassignModuleFromStudentButton.addActionListener(new unassignModuleHandler());
        submitModuleChoiceForButton.addActionListener(new submitButtonHandler());

        for (Student student : Controller.getStudents()) {
            studentModel.addElement(student.getLogin());
        }

        studentList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent evt) {
                if (!evt.getValueIsAdjusting()) {
                    choiceModel.setRowCount(0);
                    chosenModel.setRowCount(0);
                    for (Student student : Controller.getStudents()) {
                        System.out.println(student.getLogin());
                        if (student.getLogin().equals(studentList.getSelectedValue())) {
                            studentName.setText(student.getForename() + " " + student.getSurname());
                            for (PeriodOfStudy p : Controller.getPeriodsOfStudy()) {
                                if (p.getLoginID().equals(studentList.getSelectedValue())) {
                                    periodOfStudyLabel = p.getLabel();
                                    studentLevel.setText(p.getLevelOfStudy());
                                }
                            }
                            for (ModuleDegree m : Controller.getModuleDegrees()) {
                                if (m.getDegreeCode().equals(student.getDegreeCode()) && (m.getDegreeLevel().equals(studentLevel.getText()))) {
                                    for (Module mod : Controller.getModules()) {
                                        if (mod.getCode().equals(m.getModuleCode())) {
                                            if (m.isCore())
                                                choiceModel.addRow(new Object[]{m.getModuleCode(), mod.getCredits(), "Core"});
                                            else
                                                choiceModel.addRow(new Object[]{m.getModuleCode(), mod.getCredits(), "Not Core"});
                                        }
                                    }
                                }
                            }
                            for (Grade grade : Controller.getModuleDegrees()){

                            }
                        }
                    }
                    calculateCredits();
                }
            }
        });
    }

    private void calculateCredits() {
        int total = 0;
        for (int i = 0; i < chosenModel.getRowCount(); i++) {
            int credits = Integer.parseInt(chosenModel.getValueAt(i, 1).toString());
            total = total + credits;
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
        panel1.setLayout(new GridLayoutManager(9, 6, new Insets(0, 0, 0, 0), -1, -1));
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
        assignModuleToStudentButton = new JButton();
        assignModuleToStudentButton.setText("Assign Module to Student");
        panel1.add(assignModuleToStudentButton, new GridConstraints(5, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        unassignModuleFromStudentButton = new JButton();
        unassignModuleFromStudentButton.setText("Unassign Module from Student");
        panel1.add(unassignModuleFromStudentButton, new GridConstraints(5, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        submitModuleChoiceForButton = new JButton();
        submitModuleChoiceForButton.setText("Submit Module Choice for Student");
        panel1.add(submitModuleChoiceForButton, new GridConstraints(7, 0, 1, 6, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("Total Credits Selected:");
        panel1.add(label4, new GridConstraints(6, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        totalCredits = new JLabel();
        totalCredits.setText("");
        panel1.add(totalCredits, new GridConstraints(6, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        backButton = new JButton();
        backButton.setText("Back");
        panel1.add(backButton, new GridConstraints(8, 0, 1, 6, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
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

    public class assignModuleHandler implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            if (choiceTable.getSelectedRow() != -1) {
                int rowNumber = choiceTable.getSelectedRow();
                String code = choiceTable.getValueAt(rowNumber,0).toString();
                //String cred = choiceTable.getValueAt(rowNumber,1).toString();
                //String coreStatus = choiceTable.getValueAt(rowNumber,2).toString();
                Controller.saveBlankGrades(studentList.getSelectedValue().toString(), code, periodOfStudyLabel);
                //chosenModel.addRow(new Object[]{code, cred, coreStatus});
                //choiceModel.removeRow(rowNumber);
            }
            calculateCredits();
        }
    }

    public class submitButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            String level = studentLevel.getText();
            String sumCredits = totalCredits.getText();
            if ((level.equals("4")&&sumCredits.equals("180"))||(Integer.parseInt(level)<4&&sumCredits.equals("120"))){
                for (int i = 0; i < chosenModel.getRowCount(); i++) {
                    String module = chosenTable.getValueAt(i, 0).toString();
                    Controller.saveBlankGrades(studentList.getSelectedValue().toString(), module, periodOfStudyLabel);
                }
            }
        }
    }

    public class unassignModuleHandler implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            if (chosenTable.getSelectedRow() != -1) {
                int rowNumber = chosenTable.getSelectedRow();
                if (chosenTable.getValueAt(rowNumber,2).equals("Not Core")) {
                    String code = chosenTable.getValueAt(rowNumber,0).toString();
                    String credits = chosenTable.getValueAt(rowNumber,1).toString();
                    String coreStatus = chosenTable.getValueAt(rowNumber,2).toString();
                    choiceModel.addRow(new Object[]{code, credits, coreStatus});
                    chosenModel.removeRow(chosenTable.getSelectedRow());
                }
            }
            calculateCredits();
        }
    }

}
