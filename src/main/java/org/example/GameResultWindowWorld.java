package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Random;
import java.util.stream.Collectors;

import static org.example.UserTurn.pointCounter;

public class GameResultWindowWorld extends JFrame {

    public GameResultWindowWorld() {
        setTitle("Результат гри: " + (pointCounter - 1));
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        ImageIcon image = new ImageIcon("globe.png");
        Image scaledImage = image.getImage().getScaledInstance(240, 300, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel imageLabel = new JLabel(scaledIcon);

        JLabel messageLabel = new JLabel();
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        messageLabel.setText(generateMessage());

        JPanel panelNorth = new JPanel();
        panelNorth.setLayout(new BoxLayout(panelNorth, BoxLayout.X_AXIS));
        panelNorth.add(imageLabel);
        panelNorth.add(Box.createHorizontalGlue());
        panelNorth.add(messageLabel);

        JButton restartButton = new JButton("Спробувати ще раз");
        restartButton.setPreferredSize(new Dimension(300, 30));
        restartButton.setBackground(Color.BLUE);

        ImageIcon imageIcon = new ImageIcon("/repeat.png");
        Image repeat = imageIcon.getImage();
        Image scaledRepeat = repeat.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        ImageIcon scaledIconRepeat = new ImageIcon(scaledRepeat);
        restartButton.setIcon(scaledIconRepeat);
        restartButton.setFont(new Font("Arial", Font.BOLD, 14));

        JPanel panelButton = new JPanel();
        panelButton.add(restartButton);
        panelButton.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));

        add(panelNorth, BorderLayout.NORTH);
        add(panelButton, BorderLayout.SOUTH);
        getRootPane().setDefaultButton(restartButton);

        restartButton.addActionListener(e -> {
            dispose();
            new WelcomeWindow();
        });

        setVisible(true);
    }

    public String generateMessage() {
        HashSet<String> messages = new HashSet<>();
        messages.add("<html>Ти - справжній географічний ніндзя! Із такими знаннями, ти завоюєш світ!");
        messages.add("<html>Ти взяв географію у кільце і пішов на розбірки з містами. Залиши тут свій автограф!");
        messages.add("<html>Ти впіймав всі міста світу, як рибалка впіймав рибину. Ти - географічний майстер!");
        messages.add("<html>Ти знаєш міста як свої п'ять пальців! Вже можеш відправлятися дослідувати на Марс!");
        messages.add("<html>Ти так круто знаєш міста, що географи тобі заздрять. Ти - вищий рівень географії!");
        messages.add("<html>Ти - шеф географічних гонок! Інші гравці можуть тільки мріяти про твої знання!");
        messages.add("<html>Ти знаєш міста світу на відмінно! Це як світова подорож без виходу з дому!!");
        messages.add("<html>Ти - Містер/Міс Глобус! Твої знання про міста настільки широкі, що здивовують навіть пташок на небі!");
        messages.add("<html>Ти - справжній географічний ерудит! Твої знання про міста вразять навіть вчених з Національної географічної асоціації!");
        messages.add("<html>Ти - географічний гурман! Твої знання про міста - це шедевр, як Ейфелева вежа!");
        return messages.stream().sorted(Comparator.comparingInt(o -> new Random().nextInt())).limit(1).collect(Collectors.joining());
    }
}


