package src.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateDegree extends src.gui.Form {
    private JPanel panel1;
    private JTextField degreeCode;
    private JTextField degreeName;
    private JButton createDegree;

    public class CreateDegreeHandler implements ActionListener {
        private src.db_handler.DatabaseHandler db;

        @Override
        public void actionPerformed(ActionEvent e) {
            db = new src.db_handler.DatabaseHandler();
            src.db_handler.AdditionQueries additionQ = new src.db_handler.AdditionQueries(db); //^TODO CHECK OUT THIS ERROR
            additionQ.addDegree(degreeCode.getText(), degreeName.getText());
            JOptionPane.showMessageDialog(null, "Degree " + degreeCode.getText() + " Created");
        }
    }

    public CreateDegree(GUIFrame frame) {
        super(frame);
        setJPanel(panel1);
        createDegree.addActionListener(new CreateDegreeHandler());
    }

}
