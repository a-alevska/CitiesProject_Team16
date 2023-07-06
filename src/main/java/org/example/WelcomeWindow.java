package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeWindow extends JFrame {
    private final ImageIcon img = new ImageIcon("icon.png");

    public WelcomeWindow() {
        this.setIconImage(img.getImage());
        setTitle("Гра Міста");
        setSize(400, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel label = new JLabel("Ласкаво просимо до гри Міста!");
        JButton startButton = new JButton("Старт");
        getRootPane().setDefaultButton(startButton);

        JPanel panel = new JPanel();
        panel.add(label);
        panel.add(startButton);

        add(panel);

        setVisible(true);

        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Закрити вітальне вікно
                UserTurn userTurn = new UserTurn();
                userTurn.makeMove();
            }
        });
    }
}