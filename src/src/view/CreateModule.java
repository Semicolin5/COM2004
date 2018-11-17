package src.view;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.*;
import src.objects.Degree;
import src.controller.Controller;

/**
 * CreateModdule.java
 * Only accessible for Administrators (privilege level 4)
 * Extension of form, creates a functional GUI form which allows
 * user to create a new Module as an entry in the module table, and also
 * to add any degrees/levels which the module is approved for and its core status,
 * which are added as rows in the core table.
 */
public class CreateModule extends Form {
    private JPanel myPanel;
    private JTextField moduleCode;
    private JTextField moduleName;
    private JTextField moduleCredits;
    private JComboBox degreeCombo;
    private JComboBox levelCombo;
    private JComboBox coreCombo;
    private JButton linkButton;
    private JList moduleList;
    private JButton createModuleButton;
    private DefaultListModel<String> departmentsModel;

    /**
     * Set default JFrame sizes & add Event Listeners & Item Listener.
     * Also adds values to the different JComboBoxes.
     * @param frame - JFrame with properties set in the GUIFrame class.
     */
    public CreateModule(GUIFrame frame) {
        super(frame);
        setJPanel(myPanel);
        frame.setTitle("Create Module Screen");

        degreeCombo.addItem("");
        coreCombo.addItem("Core");
        coreCombo.addItem("Not Core");
        departmentsModel = new DefaultListModel<>();
        moduleList.setModel(departmentsModel);
        moduleList.setVisibleRowCount(10);
        //loops through degrees in database and adds all of their codes to the JComboBox.
        for (Degree degree : src.controller.Controller.getDegrees()) {
            degreeCombo.addItem(degree.getDegreeCode());
        }

        /**
         * Item Listener which updates the levelsCombo depending on which value
         * is selected in  the degreeCombo JComboBox.
         */
        degreeCombo.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                levelCombo.removeAllItems();
                for (Degree degree : src.controller.Controller.getDegrees()) {
                    if (degree.getDegreeCode().equals(e.getItem()) && degree.getDegreeType()) {
                        for (int i = 1; i < 5; i++)
                            levelCombo.addItem(i);
                    } else if (degree.getDegreeCode().equals(e.getItem())) {
                        for (int i = 1; i < 4; i++)
                            levelCombo.addItem(i);
                    }
                }
            }
        });
        //TODO: Can we call the item listener in the constructor, so that the...
        //TODO: Continued: ...second combobox autmotaically loads based on the value of the first?
        linkButton.addActionListener(new LinkHandler());
        createModuleButton.addActionListener(new CreateModuleHandler());
    }

    /**
     * LinkHandler checks whether the data currently in the three JComboBoxes is appropriate
     * Before adding the info to the JList.
     */
    public class LinkHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //TODO: Run length/form/duplicate checks here.
            //TODO: Check that the module code is not already present in the JList.
            //TODO: Check that the degree & module choice aren't forming a duplicate primary key.
            //TODO: Colin, do we need length/form checks here? If we have correct checks on the degree data we are saving.
            //TODO: 't all data in here already be correct? Other than the potential for the first JComboBox being blank.
            //TODO: idea is that we know that data is correct before it is added to the JList.
            String details = degreeCombo.getSelectedItem().toString() + " " +
                    levelCombo.getSelectedItem().toString() + " " + coreCombo.getSelectedItem().toString();
            departmentsModel.addElement(details);
        }
    }

    /**
     * Action Handler which calls functions which add a row to the module table.
     * Also adds a row to the core table for each degree level which this module is approved for.
     */
    public class CreateModuleHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //TODO: Check that text entered into the first three textboxes meets format/length/duplication checks before runnimg this.
            //We should already know that data in the JList is in the correct format here, as we checked it before adding to the JList.
            ListModel model = moduleList.getModel();
            //System.out.println("Module Code is " + moduleCode.getText());
            //System.out.println("Module Name is " + moduleName.getText());
            //System.out.println("Credits is " + moduleCredits.getText());
            Controller.saveModule(moduleCode.getText(),moduleName.getText(),Integer.parseInt(moduleCredits.getText()));

            for(int i=0; i < model.getSize(); i++){
                Object o =  model.getElementAt(i);
                String arr [] = o.toString().split(" ");
                String degreeCode = arr[0];
                int level = Integer.parseInt(arr[1]);
                if(arr[2].equals("Core")) {
                    //System.out.println("Module Code is " + moduleCode.getText());
                    //System.out.println("Degree Code is " + degreeCode);
                    //System.out.println("Level is " + level);
                    //System.out.println("boolean true");
                    Controller.saveModuleAssociation(moduleCode.getText(),degreeCode,level,true);
                }
                else {
                    Controller.saveModuleAssociation(moduleCode.getText(), degreeCode,level,false);
                    //System.out.println("Module Code is " + moduleCode.getText());
                    //System.out.println("Degree Code is " + degreeCode);
                    //System.out.println("Level is " + level);
                    //System.out.println("boolean false");
                }
            }
        }
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
        myPanel = new JPanel();
        myPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(12, 10, new Insets(0, 0, 0, 0), -1, -1));
        final JLabel label1 = new JLabel();
        label1.setText("Module Code");
        myPanel.add(label1, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Module Name");
        myPanel.add(label2, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Module Credits");
        myPanel.add(label3, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        moduleCode = new JTextField();
        moduleCode.setText("");
        myPanel.add(moduleCode, new com.intellij.uiDesigner.core.GridConstraints(1, 2, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        moduleName = new JTextField();
        myPanel.add(moduleName, new com.intellij.uiDesigner.core.GridConstraints(2, 2, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        moduleCredits = new JTextField();
        myPanel.add(moduleCredits, new com.intellij.uiDesigner.core.GridConstraints(3, 2, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("List of Related Degrees");
        myPanel.add(label4, new com.intellij.uiDesigner.core.GridConstraints(7, 2, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        myPanel.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(11, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer2 = new com.intellij.uiDesigner.core.Spacer();
        myPanel.add(spacer2, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setText("Module Details");
        myPanel.add(label5, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer3 = new com.intellij.uiDesigner.core.Spacer();
        myPanel.add(spacer3, new com.intellij.uiDesigner.core.GridConstraints(10, 9, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer4 = new com.intellij.uiDesigner.core.Spacer();
        myPanel.add(spacer4, new com.intellij.uiDesigner.core.GridConstraints(9, 8, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        myPanel.add(scrollPane1, new com.intellij.uiDesigner.core.GridConstraints(8, 2, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        moduleList = new JList();
        scrollPane1.setViewportView(moduleList);
        final JLabel label6 = new JLabel();
        label6.setText("Degree Code");
        myPanel.add(label6, new com.intellij.uiDesigner.core.GridConstraints(4, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label7 = new JLabel();
        label7.setText("Degree Level");
        myPanel.add(label7, new com.intellij.uiDesigner.core.GridConstraints(4, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer5 = new com.intellij.uiDesigner.core.Spacer();
        myPanel.add(spacer5, new com.intellij.uiDesigner.core.GridConstraints(6, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JLabel label8 = new JLabel();
        label8.setText("Core Status");
        myPanel.add(label8, new com.intellij.uiDesigner.core.GridConstraints(4, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        degreeCombo = new JComboBox();
        myPanel.add(degreeCombo, new com.intellij.uiDesigner.core.GridConstraints(5, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        levelCombo = new JComboBox();
        myPanel.add(levelCombo, new com.intellij.uiDesigner.core.GridConstraints(5, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        coreCombo = new JComboBox();
        myPanel.add(coreCombo, new com.intellij.uiDesigner.core.GridConstraints(5, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        linkButton = new JButton();
        linkButton.setText("Link to Degree Level");
        myPanel.add(linkButton, new com.intellij.uiDesigner.core.GridConstraints(5, 5, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label9 = new JLabel();
        label9.setText("Add Related Degree");
        myPanel.add(label9, new com.intellij.uiDesigner.core.GridConstraints(5, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        createModuleButton = new JButton();
        createModuleButton.setText("Create Module");
        myPanel.add(createModuleButton, new com.intellij.uiDesigner.core.GridConstraints(10, 2, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return myPanel;
    }
}


