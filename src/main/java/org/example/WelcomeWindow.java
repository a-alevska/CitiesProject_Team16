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
        getRootPane().setDefaultButton(startButton);
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Закрити вітальне вікно
                new GameWindow(); // Відкрити вікно гри
                new UserTurn().makeMove();
            }
        });

        JPanel panel = new JPanel();
        panel.add(label);
        panel.add(startButton);

        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openGameWindow();
            }
        });

        add(panel);

        setVisible(true);
    }

    private void openGameWindow() {
        GameWindow gameWindow = new GameWindow();
        gameWindow.setVisible(true);
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(WelcomeWindow::new);
    }
}