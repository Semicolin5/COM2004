package src.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DepartmentMain extends Form {
    private JPanel panel;

    public DepartmentMain(GUIFrame frame) {
        super(frame);
        panel = initPanel();
        setJPanel(panel);
    }

    private JPanel initPanel() {
        JButton createDepartment = new JButton("Create Department");

        createDepartment.setText("Create Department");
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panel.add(createDepartment);
        panel.add(new JButton("Delete Department"));
        createDepartment.addActionListener(new DepartmentHandler());
        return panel;
    }

    public class DepartmentHandler implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            changeJPanel(new CreateDepartment(getFrame()).getJPanel());
        }
    }

}

