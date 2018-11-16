package src.view;

import src.controller.Main;
import src.model.db_handler.DatabaseHandler;

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
        JButton courseButton = new JButton("Course Management");
        JButton departmentButton = new JButton("Department Management");
        JButton userButton = new JButton("User Management");
        JButton moduleButton = new JButton("Module Management");
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        int privilege = Main.getPriv();

        //Admin buttons
        if(privilege == 4) {
            panel.add(userButton);
            panel.add(departmentButton);
            panel.add(courseButton);
            panel.add(moduleButton);
            departmentButton.addActionListener(new departmentButtonHandler());
            courseButton.addActionListener(new degreeButtonHandler());
            userButton.addActionListener(new userButtonHandler());
            moduleButton.addActionListener(new moduleButtonHandler());
        }
        //Registrar
        else if(privilege == 3) {
            panel.add(new JButton("Student Management"));
            panel.add(new JButton("Add/Drop Student Modules"));
        }
        //Teacher
        else if(privilege == 2) {
            panel.add(new JButton("Update Grades"));
        }
        //Student
        else {
            panel.add(new JButton("View Record"));
        }

        //Add logout button at the end
        JButton logoutButton = new JButton("Logout");
        panel.add(logoutButton);
        logoutButton.addActionListener(new logoutButtonHandler());

        return panel;
    }

    public class degreeButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            changeJPanel(new src.view.Course(getFrame()).getJPanel());
        }
    }

    private class departmentButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            changeJPanel(new src.view.RemoveDepartment(getFrame()).getJPanel());
        }
    }

    private class logoutButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            Main.getDB().closeConnection();
            exit(0);
        }
    }

    private class moduleButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            changeJPanel(new src.view.CreateModule(getFrame()).getJPanel());
        }
    }

    public class userButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            changeJPanel(new src.view.ManageUsers(getFrame()).getJPanel());

        }
    }
}
