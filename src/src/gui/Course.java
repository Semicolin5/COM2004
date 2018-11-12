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
        JButton createDegree = new JButton("Create Degree");
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panel.add(createDegree);
        createDegree.addActionListener(new CourseHandler());
        panel.add(new JButton("Delete Degree"));
        return panel;
    }

    public class CourseHandler implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            changeJPanel(new CreateDegree(getFrame()).getJPanel());
        }
    }


}
