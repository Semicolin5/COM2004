package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class CreateModule {
    private JButton createModuleButton;
    private JPanel myPanel;
    private JTextField moduleCode;
    private JTextField moduleName;
    private JTextField moduleCredits;

    public CreateModule() {
        createModuleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Module Created" );
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("CreateModule");
        frame.setContentPane(new CreateModule().myPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(550, 200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
