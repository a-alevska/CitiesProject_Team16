package org.example;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class WelcomeWindow extends JFrame {

    public WelcomeWindow() {
        ImageIcon img = new ImageIcon("icon.png");
        this.setIconImage(img.getImage());
        setTitle("Гра Міста");
        setSize(400, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JLabel label = new JLabel("Ласкаво просимо до гри Міста!");
        JButton startButton = new JButton("Старт");
        getRootPane().setDefaultButton(startButton);

        JPanel panel = new JPanel();
       panel.setBorder(new EmptyBorder(11,0 ,0 ,0));
        panel.add(label);
        panel.add(startButton);

        add(panel);

        setVisible(true);

        startButton.addActionListener(e -> {
            dispose(); // Закрити вітальне вікно
            UserTurn userTurn = new UserTurn();
            userTurn.makeMove();
        });
    }
}