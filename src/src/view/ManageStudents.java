package src.view;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import src.controller.Controller;
import src.objects.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * This class generates a GUI from which an registrar can view all students which are currently in the database.
 * In addition, the registrar can delete any students from the database, should they so desire.
 * Also contains a button which allows the user to go to the CreateStudent form.
 */
public class ManageStudents extends Form {
    private JPanel panel1;
    private JList studentList;
    private JButton deleteStudentsButton;
    private JButton createStudentButton;
    private DefaultListModel<String> studentModel;
    private JButton backButton;

    /**
     * Constructor for the ManageStudents class; draws up the GUI and loads student data into the JList.
     *
     * @param frame calls the frame method from the superclass Form.
     */
    public ManageStudents(GUIFrame frame) {
        super(frame);
        setJPanel(panel1);
        frame.setTitle("Manage Students Screen");
        studentModel = new DefaultListModel<>();

        //loops through users in database and adds all of their loginIDs to the JList.
        for (Student student : Controller.getStudents()) {
            studentModel.addElement(String.valueOf(student.getLogin()));
        }

        studentList.setLayoutOrientation(JList.VERTICAL);
        studentList.setModel(studentModel);
        studentList.setVisibleRowCount(10);

        backButton.addActionListener(new BackHandler());
        createStudentButton.addActionListener(new CreateStudentHandler());
        deleteStudentsButton.addActionListener(new RemoveStudentHandler());
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
        studentList = new JList();
        studentList.setEnabled(true);
        studentList.setLayoutOrientation(1);
        scrollPane1.setViewportView(studentList);
        final Spacer spacer1 = new Spacer();
        panel1.add(spacer1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        panel1.add(spacer2, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        deleteStudentsButton = new JButton();
        deleteStudentsButton.setText("Delete Selected Students");
        panel1.add(deleteStudentsButton, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        panel1.add(spacer3, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        createStudentButton = new JButton();
        createStudentButton.setText("Create Student");
        panel1.add(createStudentButton, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("List of Students");
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
     * Handler class which responds to button clicks on the CreateStudent button.
     * It simply closes the current GUI form and opens the CreateStudent GUI form.
     */
    public class CreateStudentHandler implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            changeJPanel(new CreateStudent(getFrame()).getJPanel());
        }
    }

    /**
     * This class acts as the handler for the RemoveStudent button.
     * When clicked, the button will extract the selected values, and then loop through all
     * students in the database, deleting them one by one when primary keys match..
     */
    private class RemoveStudentHandler implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            for (Object code : studentList.getSelectedValuesList()) {
                int codeAsInt = Integer.parseInt((String) code); // casting code to integer
                Controller.removeStudent(codeAsInt);
            }
            changeJPanel(new ManageStudents(getFrame()).getJPanel());
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
