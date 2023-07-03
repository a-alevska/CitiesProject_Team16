package org.example;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameWindow extends JFrame {
    private final JTextField inputField;
    private final JLabel computerLabel;
    private final JButton playButton;

    public GameWindow() {
        setTitle("Гра Міста");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        inputField = new JTextField();
        computerLabel = new JLabel();
        computerLabel.setText("Компьютер:");
        computerLabel.setBorder(new EmptyBorder(20, 10, 300, 20));
        playButton = new JButton("Зробити хід");

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(inputField, BorderLayout.NORTH);
        panel.add(computerLabel, BorderLayout.CENTER);
        panel.add(playButton, BorderLayout.SOUTH);

        add(panel);

        setVisible(true);
        inputField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                playButton.doClick(); // Enter = "Зробити хід"
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GameWindow::new);
    }
}

