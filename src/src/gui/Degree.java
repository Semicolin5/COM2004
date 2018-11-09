package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Degree {
    private JPanel panel1;
    private JTextField degreeCode;
    private JTextField degreeName;
    private JButton createDegree;

    public Degree() {
        createDegree.addActionListener(e -> {
            //Add function to create degree here.
                JOptionPane.showMessageDialog(null, "Degree Created" );
            });
    }

    public static void main(String[] args) {
        /**
         * Creates a GUI in Java Swing, which can be used to easily enter data
         * which will then be saved to the SQL database.
         * */
        JFrame frame = new JFrame("Degree");
        frame.setContentPane(new Degree().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(550, 140);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
