package src.gui;

import javax.swing.*;
import java.awt.*;

public class Welcome extends Form {
    private JPanel panel;

    public Welcome(GUIFrame frame) {
        super(frame);
        panel = initPanel();
        setJPanel(panel);
    }

    private JPanel initPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        int privilege = getFrame().getUser().getPriv();

        //Admin buttons
        if(privilege == 4) {
            panel.add(new JButton("User Management"));
            panel.add(new JButton("Department Management"));
            panel.add(new JButton("Course Management"));
            panel.add(new JButton("Module Management"));
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
}
