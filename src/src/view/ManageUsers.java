package src.view;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import src.controller.Controller;
import src.objects.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class generates a GUI from which an administrator can view all user logins which are currently in the database.
 * In addition, the administrator can delete any users from the database, should they so desire.
 * Also contains a button which allows the user to go to the CreateUser form.
 */
public class ManageUsers extends Form {
    private JPanel panel1;
    private JList userList;
    private JButton deleteSelectedUsersButton;
    private JButton createUserButton;
    private DefaultListModel<String> userModel;
    private JButton backButton;

    /**
     * Constructor for the ManagerUsers class; draws up the GUI and loads user data into the JList.
     *
     * @param frame calls the frame method from the superclass Form.
     */
    public ManageUsers(GUIFrame frame) {
        super(frame);

        setJPanel(panel1);
        userModel = new DefaultListModel<>();
        frame.setTitle("Manage Users Screen");

        //loops through users in database and adds all of their loginIDs to the JList.
        for (User user : Controller.getUsers()) {
            userModel.addElement(String.valueOf(user.getLogin()));
        }
        userList.setLayoutOrientation(JList.VERTICAL);
        userList.setModel(userModel);
        userList.setVisibleRowCount(10);

        backButton.addActionListener(new BackHandler());
        createUserButton.addActionListener(new UserHandler());
        deleteSelectedUsersButton.addActionListener(new RemoveUserHandler());
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
        panel1.setLayout(new GridLayoutManager(9, 1, new Insets(0, 0, 0, 0), -1, -1));
        final JScrollPane scrollPane1 = new JScrollPane();
        panel1.add(scrollPane1, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        userList = new JList();
        userList.setEnabled(true);
        userList.setLayoutOrientation(1);
        scrollPane1.setViewportView(userList);
        final Spacer spacer1 = new Spacer();
        panel1.add(spacer1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        panel1.add(spacer2, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        deleteSelectedUsersButton = new JButton();
        deleteSelectedUsersButton.setText("Delete Selected Users");
        panel1.add(deleteSelectedUsersButton, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        panel1.add(spacer3, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        createUserButton = new JButton();
        createUserButton.setText("Create User");
        panel1.add(createUserButton, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("List of Users");
        panel1.add(label1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer4 = new Spacer();
        panel1.add(spacer4, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        backButton = new JButton();
        backButton.setText("Back");
        panel1.add(backButton, new GridConstraints(8, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }

    /**
     * Handler class which responds to button clicks on the CreateUser button.
     * It simply closes the current GUI form and opens the CreateUser GUi form.
     */
    public class UserHandler implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            changeJPanel(new CreateUser(getFrame()).getJPanel());
        }
    }

    /**
     * This class acts as the handler for the RemoveUser button.
     * When clicked, the button will extract the selected values, and then loop through all
     * users in the database, deleting them one by one when primary keys match..
     */
    private class RemoveUserHandler implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            for (Object code : userList.getSelectedValuesList()) {
                // if the Admin is trying to delete a student, an error message is prompted, and the user isn't deleted.
                if (!Controller.removeUser((String) code)) {
                    JOptionPane.showMessageDialog(getFrame(), "The selected user is a student. Only" +
                            " registrars are allowed to remove students");
                }
            }
            changeJPanel(new ManageUsers(getFrame()).getJPanel());
        }
    }

    /**
     * ActionListener class which takes the user back to the Welcome form.
     */
    private class BackHandler implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            changeJPanel(new Welcome(getFrame()).getJPanel());
        }
    }

}
