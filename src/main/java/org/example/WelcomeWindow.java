package org.example;

import javax.swing.*;

public class WelcomeWindow extends JFrame {
   private final JLabel label;
    private final JButton startButton;

    public WelcomeWindow() {
        setTitle("Гра Міста");
        setSize(400, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        label = new JLabel("Ласкаво просимо до гри Міста!");
        startButton = new JButton("Старт");

        JPanel panel = new JPanel();
        panel.add(label);
        panel.add(startButton);

        add(panel);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(WelcomeWindow::new);
    }
}
