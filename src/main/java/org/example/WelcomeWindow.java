package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeWindow extends JFrame {
    private JLabel label;
    private JButton startButton;

    public WelcomeWindow() {
        setTitle("Гра Міста");
        setSize(400, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        label = new JLabel("Ласкаво просимо до гри Міста!");
        startButton = new JButton("Старт");
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Закрити вітальне вікно
                new GameWindow(); // Відкрити вікно гри
            }
        });

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