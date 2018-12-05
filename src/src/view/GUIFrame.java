package src.view;

import javax.swing.*;

import src.controller.Main;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GUIFrame extends JFrame {

    private JPanel currentPanel;

    public GUIFrame() {
        currentPanel = new Login(this).getJPanel();
        setContentPane(currentPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(1200,600);
        setLocationRelativeTo(null);
        setTitle("");

        //When the window is closed, cleanly shutdown the DB connection
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                super.windowClosing(windowEvent);
                Main.getDB().closeConnection();
            }
        });
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
