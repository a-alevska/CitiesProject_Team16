package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameWindow extends JFrame {
    private final JTextField inputField;
    private final JLabel computerLabel;
    private final JButton playButton;

    public GameWindow() {
        setTitle("Гра Міста");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        inputField = new JTextField();
        computerLabel = new JLabel();
        playButton = new JButton("Зробити хід");

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(inputField, BorderLayout.NORTH);
        panel.add(computerLabel, BorderLayout.CENTER);
        panel.add(playButton, BorderLayout.SOUTH);

        add(panel);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GameWindow::new);
    }
}

