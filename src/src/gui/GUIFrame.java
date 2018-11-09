package src.gui;

import javax.swing.*;

public class GUIFrame extends JFrame {

    private JPanel currentPanel;

    public GUIFrame() {
        currentPanel = new Login(this).getJPanel();
        setContentPane(currentPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void changeJPanel(JPanel panel) {
        currentPanel.removeAll();
        getContentPane().setVisible(false);
        getContentPane().remove(currentPanel);
        currentPanel = panel;
        getContentPane().add(currentPanel);
        getContentPane().setVisible(true);
    }

    public static void main(String args[]) {
        new GUIFrame();
    }
}
