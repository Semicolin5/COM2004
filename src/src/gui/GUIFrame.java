package src.gui;

import src.objects.User;

import javax.swing.*;

public class GUIFrame extends JFrame {

    private JPanel currentPanel;
    private User user;

    public GUIFrame(User user) {
        this.user = user;
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
        new GUIFrame(new User("Login", "Pass", 1));
    }

    public User getUser() {
        return user;
    }
}
