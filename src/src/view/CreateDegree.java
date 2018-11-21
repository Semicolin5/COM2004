package src.view;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import src.objects.Department;
import src.controller.Controller;

/**
 * CreateDegree.java
 * Only accessible for Administrators (privilege level 4)
 * Extension of form, creates a functional GUI form which allows
 * user to create a new degree as an entry in the degree table.
 */
public class CreateDegree extends Form {
    private JPanel panel1;
    private JTextField degreeCode;
    private JTextField degreeName;
    private JButton createDegree;
    private JComboBox mastersCombo;
    private JComboBox yearIndustryCombo;
    private JComboBox departmentCombo;
    private JComboBox leadCombo;
    private JButton linkDepartmentButton;
    private JList departmentList;
    private DefaultListModel<String> departmentsModel;

    /**
     * Set default JFrame sizes & add Event Listener
     *
     * @param frame - JFrame with properties set in the GUIFrame class.
     */
    public CreateDegree(GUIFrame frame) {
        super(frame);
        setJPanel(panel1);
        createDegree.addActionListener(new CreateDegreeHandler());

        departmentsModel = new DefaultListModel<>();
        departmentList.setModel(departmentsModel);
        departmentList.setVisibleRowCount(10);
        //loops through degrees in database and adds all of their codes to the JComboBox.
        for (Department department : Controller.getDepartments()) {
            departmentCombo.addItem(department.getCode());
        }
        linkDepartmentButton.addActionListener(new LinkHandler());
        createDegree.addActionListener(new CreateDegreeHandler());
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
        panel1.setLayout(new GridLayoutManager(12, 5, new Insets(0, 0, 0, 0), -1, -1));
        final JLabel label1 = new JLabel();
        label1.setText("Degree Code");
        panel1.add(label1, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Degree Name");
        panel1.add(label2, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        degreeCode = new JTextField();
        panel1.add(degreeCode, new GridConstraints(1, 2, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        degreeName = new JTextField();
        panel1.add(degreeName, new GridConstraints(2, 2, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel1.add(spacer1, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        panel1.add(spacer2, new GridConstraints(10, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        panel1.add(spacer3, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Bachelors or Masters?");
        panel1.add(label3, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        mastersCombo = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        defaultComboBoxModel1.addElement("Bachelors");
        defaultComboBoxModel1.addElement("Masters");
        mastersCombo.setModel(defaultComboBoxModel1);
        panel1.add(mastersCombo, new GridConstraints(3, 2, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("Includes Placement Year?");
        panel1.add(label4, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        createDegree = new JButton();
        createDegree.setText("Create Degree");
        panel1.add(createDegree, new GridConstraints(10, 2, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer4 = new Spacer();
        panel1.add(spacer4, new GridConstraints(11, 2, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        yearIndustryCombo = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel2 = new DefaultComboBoxModel();
        defaultComboBoxModel2.addElement("Exclues Year In Industry");
        defaultComboBoxModel2.addElement("Includes Year In Industry");
        yearIndustryCombo.setModel(defaultComboBoxModel2);
        panel1.add(yearIndustryCombo, new GridConstraints(4, 2, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer5 = new Spacer();
        panel1.add(spacer5, new GridConstraints(5, 2, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setText("Add Related Department");
        panel1.add(label5, new GridConstraints(7, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        departmentCombo = new JComboBox();
        panel1.add(departmentCombo, new GridConstraints(7, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        leadCombo = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel3 = new DefaultComboBoxModel();
        defaultComboBoxModel3.addElement("Lead");
        defaultComboBoxModel3.addElement("Not Lead");
        leadCombo.setModel(defaultComboBoxModel3);
        panel1.add(leadCombo, new GridConstraints(7, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        linkDepartmentButton = new JButton();
        linkDepartmentButton.setText("Link Department");
        panel1.add(linkDepartmentButton, new GridConstraints(7, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label6 = new JLabel();
        label6.setText("List of Related Departments");
        panel1.add(label6, new GridConstraints(8, 2, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        panel1.add(scrollPane1, new GridConstraints(9, 2, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        departmentList = new JList();
        scrollPane1.setViewportView(departmentList);
        final JLabel label7 = new JLabel();
        label7.setText("Department Code");
        panel1.add(label7, new GridConstraints(6, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label8 = new JLabel();
        label8.setText("Lead Status");
        panel1.add(label8, new GridConstraints(6, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }

    public class LinkHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //TODO: Run length/form/duplicate checks here.
            //TODO: Check that the module code is not already present in the JList.
            //TODO: Check that the degree & module choice aren't forming a duplicate primary key.
            //TODO: Colin, do we need length/form checks here? If we have correct checks on the degree data we are saving.
            //TODO: 't all data in here already be correct? Other than the potential for the first JComboBox being blank.
            //TODO: idea is that we know that data is correct before it is added to the JList.
            String details = departmentCombo.getSelectedItem().toString() + " " +
                    leadCombo.getSelectedItem().toString();
            departmentsModel.addElement(details);
        }
    }

    /**
     * Event listener which passes parameters to a controller class,
     * which ultimately creates a new row in the Degree table with those parameters.
     */
    public class CreateDegreeHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //TODO: Check Length here + other necessary checks
            System.out.println(mastersCombo.getSelectedIndex());
            System.out.println(yearIndustryCombo.getSelectedIndex());
            if (mastersCombo.getSelectedIndex() == 1) {
                if (yearIndustryCombo.getSelectedIndex() == 1)
                    Controller.saveDegree(degreeCode.getText(), degreeName.getText(), true, true);
                else {
                    Controller.saveDegree(degreeCode.getText(), degreeName.getText(), true, false);
                }
            } else if (yearIndustryCombo.getSelectedIndex() == 0) {
                System.out.println("0, 0");
                Controller.saveDegree(degreeCode.getText(), degreeName.getText(), false, false);
            } else {
                Controller.saveDegree(degreeCode.getText(), degreeName.getText(), false, true);
            }
            changeJPanel(new src.view.ManageDegrees(getFrame()).getJPanel());
        }
    }

}
