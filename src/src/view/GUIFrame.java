package src.view;

import javax.swing.*;

public class GUIFrame extends JFrame {

    private JPanel currentPanel;

    public GUIFrame() {
        currentPanel = new Login(this).getJPanel();
        setContentPane(currentPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setSize(800,500);
        setLocationRelativeTo(null);
        setTitle("");
    }

    public void changeJPanel(JPanel panel) {
        currentPanel.removeAll();
        getContentPane().setVisible(false);
        getContentPane().remove(currentPanel);
        currentPanel = panel;
        getContentPane().add(currentPanel);
        getContentPane().setVisible(true);
    }

}
