package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateDegree {
    private JPanel panel1;
    private JTextField degreeCode;
    private JTextField degreeName;
    private JButton createDegree;

    public CreateDegree() {
        createDegree.addActionListener(new ActionListener() {
            private src.db_handler.DatabaseHandler db;
            @Override
            public void actionPerformed(ActionEvent e) {
                db = new src.db_handler.DatabaseHandler();
                src.db_handler.AdditionQueries additionQ = new src.db_handler.AdditionQueries(db); //^TODO CHECK OUT THIS ERROR
                additionQ.addDegree(degreeCode.getText(), degreeName.getText());
                JOptionPane.showMessageDialog(null, "Degree " +  degreeCode.getText() + " Created");
            }
        });
    }

    public static void main(String[] args) {
        /**
         * Creates a GUI in Java Swing, which can be used to easily enter data
         * which will then be saved to the SQL database.
         * */
        JFrame frame = new JFrame("CreateDegree");
        frame.setContentPane(new CreateDegree().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(550, 140);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
