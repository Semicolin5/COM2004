package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class createDepartment {
    private JPanel panel1;
    private JTextField departmentCode;
    private JTextField departmentName;
    private JButton createDepartment;

    public createDepartment() {
        createDepartment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Department Created" );
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("createDepartment");
        frame.setContentPane(new createDepartment().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(550, 140);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
