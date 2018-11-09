package gui;

import javax.swing.*;

public class createDegree {
    private JPanel panel1;
    private JTextField degreeCode;
    private JTextField degreeName;
    private JButton createDegree;

    public createDegree() {
        createDegree.addActionListener(e -> {
            //Add function to create degree here.
                JOptionPane.showMessageDialog(null, "createDegree Created" );
            });
    }

    public static void main(String[] args) {
        /**
         * Creates a GUI in Java Swing, which can be used to easily enter data
         * which will then be saved to the SQL database.
         * */
        JFrame frame = new JFrame("createDegree");
        frame.setContentPane(new createDegree().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(550, 140);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
