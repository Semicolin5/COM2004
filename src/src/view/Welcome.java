package src.view;

import src.controller.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.System.exit;

public class Welcome extends Form {
    private JPanel panel;

    public Welcome(GUIFrame frame) {
        super(frame);
        panel = initPanel();
        setJPanel(panel);
        frame.setTitle("Welcome Screen");
    }

    private JPanel initPanel() {
        JButton courseButton = new JButton("Degree Management");
        JButton departmentButton = new JButton("Department Management");
        JButton userButton = new JButton("User Management");
        JButton moduleButton = new JButton("Module Management");
        JButton studentButton = new JButton("Student Management");
        JButton pickModuleButton = new JButton("Add/Drop Student Modules");
        JButton updateGradesButton = new JButton("Update Grades");
        JButton viewStudentRecordButton = new JButton("View Record");
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        int privilege = Main.getPriv();
        System.out.println(privilege);

        //Admin buttons
        if(privilege == 4) {
            panel.add(userButton);
            panel.add(departmentButton);
            panel.add(courseButton);
            panel.add(moduleButton);
            departmentButton.addActionListener(new AdminHandler());
            courseButton.addActionListener(new AdminHandler());
            userButton.addActionListener(new AdminHandler());
            moduleButton.addActionListener(new AdminHandler());
        }
        //Registrar buttons
        else if(privilege == 3) {
            panel.add(studentButton);
            panel.add(pickModuleButton);
            pickModuleButton.addActionListener(new RegistrarHandler());
            studentButton.addActionListener(new RegistrarHandler());
        }
        //Teacher
        else if(privilege == 2) {
            panel.add(updateGradesButton);
            updateGradesButton.addActionListener(new updateGradesHandler());
        }
        //Student
        else {
            panel.add(viewStudentRecordButton);
            viewStudentRecordButton.addActionListener(new StudentHandler());
        }

        //Add logout button
        JButton backButton = new JButton("Logout");
        panel.add(backButton);
        backButton.addActionListener(new logoutButtonHandler());

        return panel;
    }

    private class AdminHandler implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            String command = actionEvent.getActionCommand();
            if (command.equals("Degree Management"))
                changeJPanel(new src.view.ManageDegrees(getFrame()).getJPanel());
            if (command.equals("Department Management"))
                changeJPanel(new src.view.ManageDepartments(getFrame()).getJPanel());
            if (command.equals("User Management"))
                changeJPanel(new src.view.ManageUsers(getFrame()).getJPanel());
            if (command.equals("Module Management"))
                changeJPanel(new src.view.ManageModules(getFrame()).getJPanel());
        }
    }

    private class RegistrarHandler implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            String command = actionEvent.getActionCommand();
            if (command.equals("Add/Drop Student Modules"))
                changeJPanel(new src.view.ModulePick(getFrame()).getJPanel());
            if (command.equals("Student Management"))
                changeJPanel(new src.view.ManageStudents(getFrame()).getJPanel());
        }
    }

    /**
     * Class that handles the student's navigation from the welcome page.
     * Method actionPerformed allows the student to navigate to new pages.
     * */
    private class StudentHandler implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            String command = actionEvent.getActionCommand();
            if (command.equals("View Record"))
                System.out.println("changing to view record");
        }
    }

    private class logoutButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            //Main.getDB().closeConnection();
            //exit(0);
            //new GUIFrame();
            //db = new DatabaseHandler();
            changeJPanel(new src.view.Login(getFrame()).getJPanel());
            //TODO: Can someone get this working please?
        }
    }

    public class updateGradesHandler implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            changeJPanel(new src.view.UpdateGrades(getFrame()).getJPanel());
        }
    }
}
