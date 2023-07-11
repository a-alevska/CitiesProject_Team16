package org.example;


import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import javax.imageio.ImageIO;
import javax.swing.*;

import static org.example.UserTurn.pointCounter;


public class ImageClick extends JPanel implements ActionListener {

    private final List<Point> squarePositions = new ArrayList<>();

    public ImageClick() {

        setPreferredSize(new Dimension(490, 270));
        generateSquarePositions(432, 230);
        generateSquarePositions(440, 230);
        generateSquarePositions(448, 230);
        generateSquarePositions(345, 240);
        generateSquarePositions(365, 240);
        generateSquarePositions(270, 240);
        generateSquarePositions(115, 230);
        generateSquarePositions(177, 170);
        generateSquarePositions(11, 290);
        generateSquarePositions(18, 290);
        generateSquarePositions(26, 290);

        Timer timer = new Timer(2000, this);
        timer.start();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Collections.shuffle(squarePositions);
        repaint();
    }

    private void generateSquarePositions(int startX, int startY) {
        int spacing = 20;
        for (int i = 0; i < 5; i++) {
            int y = startY - i * spacing;
            squarePositions.add(new Point(startX, y));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        try {
            ImageIcon imageIcon = new ImageIcon(ImageIO.read(Objects.requireNonNull(GameResultWindow.class.getResource("/logo.png"))));
            Image image = imageIcon.getImage();
            int panelWidth = getWidth();
            int panelHeight = getHeight();

            int imageWidth = image.getWidth(null);
            int imageHeight = image.getHeight(null);
            double scale = Math.min((double) panelWidth / imageWidth, (double) panelHeight / imageHeight);

            int scaledWidth = (int) (imageWidth * scale);
            int scaledHeight = (int) (imageHeight * scale);

            g.drawImage(image, 0, 0, scaledWidth, scaledHeight, null);

            g.setColor(Color.WHITE);
            for (int i = 0; i < squarePositions.size(); i++) {
                if (i < pointCounter * 5) {
                    Point position = squarePositions.get(i);
                    g.fillRect(position.x, position.y, 8, 8);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

