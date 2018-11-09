package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Degree {
    private JPanel panel1;
    private JTextField textField1;
    private JTextField textField2;
    private JButton saveButton;

    public Degree() {
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(null, "Degree Created" );
                }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Degree");
        frame.setContentPane(new Degree().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(550, 140);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
