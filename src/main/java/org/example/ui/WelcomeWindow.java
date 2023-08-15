package org.example.ui;

import org.example.logic.UserTurn;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.io.IOException;
import java.util.Objects;

public class WelcomeWindow extends JFrame {

    public WelcomeWindow() {
        try {
            ImageIcon img = new ImageIcon(ImageIO.read(Objects.requireNonNull(WelcomeWindow.class.getResource("/icon.png"))));
            this.setIconImage(img.getImage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        setTitle("Гра Міста");
        setSize(400, 100);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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
            dispose();
            UserTurn userTurn = new UserTurn();
            userTurn.makeMove();
        });
    }
}