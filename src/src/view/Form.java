package src.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Form extends JPanel {

    private GUIFrame frame;
    private JPanel panel;
    private JButton backButton;

    public Form(GUIFrame frame) {
        this.frame = frame;
    }

    public GUIFrame getFrame() {
        return frame;
    }

    public void setJPanel(JPanel panel) {
        this.panel = panel;
    }

    public JPanel getJPanel() {
        return panel;
    }

    public void changeJPanel(JPanel panel) {
        frame.changeJPanel(panel);
    }

    public void setBackButton(JButton backButton) {
        this.backButton = backButton;
    }

    public void setBackButtonPanel(JPanel panel) {
        backButton.addActionListener(new backButtonHandler(panel));
    }

    private class backButtonHandler implements ActionListener {
        private JPanel panel;

        public backButtonHandler(JPanel panel) {
            this.panel = panel;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            changeJPanel(panel);
        }
    }
}
