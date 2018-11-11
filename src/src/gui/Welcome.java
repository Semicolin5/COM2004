package src.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Welcome extends Form {
    private JPanel panel;

    public Welcome(GUIFrame frame) {
        super(frame);
        panel = initPanel();
        setJPanel(panel);

    }

    private JPanel initPanel() {
        JButton courseButton = new JButton("Course Management");
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        int privilege = getFrame().getUser().getPriv();

        //Admin buttons
        if(privilege == 4) {
            panel.add(new JButton("User Management"));
            panel.add(new JButton("Department Management"));
            panel.add(courseButton);
            panel.add(new JButton("Module Management"));
            courseButton.addActionListener(new WelcomeHandler());
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
        return panel;
    }

    public class WelcomeHandler implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            changeJPanel(new Course(getFrame()).getJPanel());
        }
    }


}
