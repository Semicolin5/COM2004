package src.view;

import javax.swing.*;

public class Form extends JPanel {

    private GUIFrame frame;
    private JPanel panel;

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
}
