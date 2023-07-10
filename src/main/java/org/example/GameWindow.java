package org.example;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class GameWindow extends JFrame {
    private final JTextField inputField;
    private final JLabel computerLabel;
    private final JButton playButton;
    private final JComboBox<String> modeComboBox;
    private final JLabel timeLabel = new JLabel();
    ImageClick imageClick = new ImageClick();


    public GameWindow() {
        ImageIcon img = new ImageIcon("resources/icon.png");
        this.setIconImage(img.getImage());
        setTitle("Гра Міста");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        inputField = new JTextField();
        computerLabel = new JLabel();
        computerLabel.setText("Компьютер:");
        playButton = new JButton("Зробити хід");
        playButton.setFont(new Font("Arial", Font.BOLD, 12));
        modeComboBox = new JComboBox<>();
        modeComboBox.addItem("Українські міста");
        modeComboBox.addItem("Міста всього світу");


        JPanel panel = new JPanel();
        JLabel cityLabel = new JLabel();
        cityLabel.setText("Введіть назву міста:");
        cityLabel.setBorder(new EmptyBorder(0,60 ,0 ,0));
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setLayout(new GridLayout(1, 2));
        panel.add(modeComboBox);
        timeLabel.setBorder(new EmptyBorder(0,60 ,0 ,0));
        panel.add(timeLabel);

        panel1.add(cityLabel);
        panel1.add(inputField);
        panel1.setLayout(new GridLayout(2, 4));
        panel1.add(playButton);
        panel1.add(computerLabel);
        panel2.add(imageClick,BorderLayout.CENTER);


        add(panel, BorderLayout.NORTH);
        add(panel1,BorderLayout.CENTER);
        add(panel2,BorderLayout.SOUTH);

        setVisible(true);


        inputField.addActionListener(e -> playButton.doClick());

    }
    public JButton getMakeMoveButton() {
        return playButton;
    }

    public JTextField getCityTextField() {
        return inputField;
    }

    public JLabel getComputerResponseLabel() {
        return computerLabel;
    }

    public JLabel getTimeLabel(){
        return timeLabel;
    }

    public JComboBox<String> getModeComboBox() {
        return modeComboBox;
    }


}

