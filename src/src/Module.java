import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Module {
    private JButton createModuleButton;
    private JPanel myPanel;
    private JTextField moduleCode;
    private JTextField module_Name;
    private JTextField moduleCredits;

    public Module() {
        createModuleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Module Created" );
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Module");
        frame.setContentPane(new Module().myPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(550, 200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
