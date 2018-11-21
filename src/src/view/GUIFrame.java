package src.view;

import javax.swing.*;

import src.controller.Main;

public class GUIFrame extends JFrame {

    private JPanel currentPanel;

    public GUIFrame() {
        currentPanel = new Login(this).getJPanel();
        setContentPane(currentPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
    
    public void privCheck() {
    	//TODO get it to check if you're actually logged in or not
    }

}
