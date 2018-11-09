package src.gui;

import javax.swing.*;

public class GUIFrame extends JFrame {

    public GUIFrame() {
        setContentPane(new Login(this).getJPanel());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String args[]) {
        new GUIFrame();
    }
}
