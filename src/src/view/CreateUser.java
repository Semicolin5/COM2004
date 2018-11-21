package src.view;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import src.controller.Controller;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * CreateUser.java
 * Only accessible for Administrators (privilege level 4)
 * Extension of form, creates a functional GUI form which allows
 * admins to create a new user as an entry in the user table, and also
 * to add any degrees/levels which the module is approved for and its core status,
 * which are added as rows in the core table.
 */
public class CreateUser extends Form {
    private JPanel panel1;
    private JTextField loginID;
    private JComboBox userType;
    private JTextField confirmPass;
    private JButton createAccountButton;
    private JPasswordField initPass;
    private int priv;

    /**
     * Constructor sets the frame and draws up the GUI.
     * Also creates an actionListener on the button.
     */
    public CreateUser(GUIFrame frame) {
        super(frame);
        setJPanel(panel1);
        createAccountButton.addActionListener(new CreateAccountHandler());
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
        panel1.setLayout(new GridLayoutManager(7, 4, new Insets(0, 0, 0, 0), -1, -1));
        loginID = new JTextField();
        panel1.add(loginID, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        userType = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        defaultComboBoxModel1.addElement("");
        defaultComboBoxModel1.addElement("Teacher");
        defaultComboBoxModel1.addElement("Registrar");
        defaultComboBoxModel1.addElement("Administrator");
        userType.setModel(defaultComboBoxModel1);
        panel1.add(userType, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("User Type");
        panel1.add(label1, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Login ID");
        panel1.add(label2, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel1.add(spacer1, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        panel1.add(spacer2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        panel1.add(spacer3, new GridConstraints(6, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Password");
        panel1.add(label3, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("Confirm Password");
        panel1.add(label4, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        createAccountButton = new JButton();
        createAccountButton.setText("Create Account");
        panel1.add(createAccountButton, new GridConstraints(5, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        initPass = new JPasswordField();
        panel1.add(initPass, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        confirmPass = new JPasswordField();
        panel1.add(confirmPass, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }

    /**
     * When button is clicked, create a user account based on the entered date.
     */
    public class CreateAccountHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //TODO: Check String length BEFORE doing toString.
            //TODO: Check LoginID is an int - Auto-generate it into a label maybe?
            //TODO: Generate Hash and salt, call the function which hashes and salts and pass through.
            //TODO: Should the passwords be in PasswordTextBoxes or does it not matter?
            String str = userType.getSelectedItem().toString();
            switch (str) {
                case "Administrator":
                    priv = 4;
                    break;
                case "Registrar":
                    priv = 3;
                    break;
                default:
                    priv = 2;
                    break;
            }

            Controller.saveUser(Integer.parseInt(loginID.getText()), confirmPass.getText(), priv);
            changeJPanel(new ManageUsers(getFrame()).getJPanel());
        }
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}