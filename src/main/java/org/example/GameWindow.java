package org.example;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameWindow extends JFrame {
    private final JTextField inputField;
    private final JLabel computerLabel;
    private final JButton playButton;
    private JComboBox<String> modeComboBox;
    private final JLabel timeLabel = new JLabel();
    private final ImageIcon img = new ImageIcon("icon.png");


    public GameWindow() {
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
        panel.add(timeLabel);
        panel1.add(cityLabel);
        panel1.add(inputField);
        panel1.setLayout(new GridLayout(2, 4));
        panel1.add(playButton);
        panel1.add(computerLabel);
        JLabel imageLabel = new JLabel();
        panel2.add(imageLabel,BorderLayout.CENTER);
        ImageIcon image=new ImageIcon("logo.png");
        Image scaledImage = image.getImage().getScaledInstance(500, 290, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        imageLabel.setIcon(scaledIcon);

        add(panel, BorderLayout.NORTH);
        add(panel1,BorderLayout.CENTER);
        add(panel2,BorderLayout.SOUTH);

        setVisible(true);


        inputField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                playButton.doClick();  // Enter = "Зробити хід"
            }
        });

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

