package org.example;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Random;
import java.util.stream.Collectors;


public class GameResultWindow extends JFrame {

    public GameResultWindow() {

        ImageIcon img = new ImageIcon("icon.png");
        this.setIconImage(img.getImage());
        setTitle("Результат гри");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        ImageIcon image = new ImageIcon("icon.png");
        Image scaledImage = image.getImage().getScaledInstance(90, 80, Image.SCALE_SMOOTH); // зміна розміру зображення
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel imageLabel = new JLabel(scaledIcon);

        JLabel messageLabel = new JLabel();
        messageLabel.setBorder(new EmptyBorder(15, 10, 0, 0));
        messageLabel.setFont(new Font("MV Boli", Font.ITALIC, 15));

        messageLabel.setText(generateMessage());

        JPanel panelNorth = new JPanel();
        panelNorth.setSize(new Dimension(500, 70));
        panelNorth.setLayout(new GridBagLayout());
        panelNorth.setLayout(new BoxLayout(panelNorth, BoxLayout.X_AXIS));
        panelNorth.add(imageLabel);
        panelNorth.add(Box.createHorizontalGlue());
        panelNorth.add(messageLabel);

        JButton restartButton = new JButton("Спробувати ще раз");

        restartButton.setPreferredSize(new Dimension(300, 30));
        restartButton.setBackground(Color.BLUE);

        ImageIcon imageIcon = new ImageIcon("repeat.png");
        Image repeat = imageIcon.getImage();
        Image scaledRepeat = repeat.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        ImageIcon scaledIconRepeat = new ImageIcon(scaledRepeat);
        restartButton.setIcon(scaledIconRepeat);
        restartButton.setFont(new Font("Comic Sans",5,14));
        JPanel panelButton = new JPanel();
        panelButton.add(restartButton);
        panelButton.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));

        add(panelNorth, BorderLayout.NORTH);

        add(panelButton, BorderLayout.SOUTH);
        add (new UkrainianFlagAnimation());

        getRootPane().setDefaultButton(restartButton);

        restartButton.addActionListener(e -> {

            dispose();
            new WelcomeWindow(); // Викликати метод для рестарту гри
        });

        setVisible(true);
    }

    public String generateMessage() {
        HashSet<String> messages = new HashSet<>();
        messages.add("<html>Вражаюче! Ти знаєш кожне місто України як свої п'ять пальців. Так тримати!");
        messages.add("<html>Ти неперевершений у грі з українськими містами! <br> Хто би міг подумати, що хтось знає їх так добре. Продовжуй так само!");
        messages.add("<html>Твої знання українських міст бездоганні! Ти справжній експерт. Нехай перемога завжди буде з тобою!");
        messages.add("<html>Ти - жива енциклопедія українських міст. Кожен твій хід - доказ твоїх неймовірних знань. Так тримати!");
        messages.add("<html>Ти справжній географ України! Твої знання про міста вражають. Успіхів у подальших грах та нових відкриттях!");
        messages.add("<html>Ти володар українських міст. Твоя впевненість та точність в кожному ході здивовують. Продовжуй домінувати на карті!");
        messages.add("<html>Чудово! Ти справжній експерт українських міст. Відправляймося до нових перемог!");
        return messages.stream().sorted(Comparator.comparingInt(o -> new Random().nextInt())).limit(1).collect(Collectors.joining());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GameResultWindow::new);

    }
}


