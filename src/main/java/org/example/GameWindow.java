package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameWindow extends JFrame {
    private final JTextField inputField;
    private final JLabel computerLabel;
    private final JButton playButton;

    public JButton getMakeMoveButton() {
        return playButton;
    }

    public JTextField getCityTextField() {
        return inputField;
    }

    public JLabel getComputerResponseLabel() {
        return computerLabel;
    }

    public GameWindow() {
        setTitle("Гра Міста");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        inputField = new JTextField();
        computerLabel = new JLabel();
        computerLabel.setText("Компютер");
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

