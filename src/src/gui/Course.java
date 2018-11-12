package src.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Course extends Form {
    private JPanel panel;

    public Course(GUIFrame frame) {
        super(frame);
        panel = initPanel();
        setJPanel(panel);
    }

    private JPanel initPanel() {
        JButton createDegree = new JButton("CreateDegree");

        createDegree.setText("Create Degree");
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panel.add(createDegree);
        panel.add(new JButton("Delete Degree"));
        createDegree.addActionListener(new CourseHandler());
        return panel;
    }

    public class CourseHandler implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            changeJPanel(new CreateDegree(getFrame()).getJPanel());
            System.out.println("CourseHandler");
        }
    }

}
