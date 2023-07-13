package org.example.ui;


import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.*;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.Timer;

import static org.example.logic.UserTurn.pointCounter;

public class ImageClick extends JPanel implements ActionListener {

    private final List<Point> squarePositions = new ArrayList<>();
    private final List<Color> colors = new ArrayList<>();

    public ImageClick() {

        setPreferredSize(new Dimension(490, 270));
        generateSquarePositions(430, 230);
        generateSquarePositions(440 , 230);
        generateSquarePositions(450, 230);
        generateSquarePositions(345, 240);
        generateSquarePositions(365, 240);
        generateSquarePositions(270, 240);
        generateSquarePositions(118, 230);
        generateSquarePositions(179, 170);
        generateSquarePositions(11, 290);
        generateSquarePositions(21, 290);
        generateSquarePositions(31, 290);

        Timer timer = new Timer(3000, this);
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

            Graphics2D g2d = (Graphics2D) g.create();

            for (int i = 0; i < squarePositions.size(); i++) {
                if (i < squarePositions.size() / 2) {
                    colors.add(Color.BLUE);
                } else {
                    colors.add(new Color(255, 210, 0));
                }
            }

            Collections.shuffle(squarePositions);
            Collections.shuffle(colors);

            for (int i = 0; i < squarePositions.size(); i++) {
                if (i < pointCounter * 5) {
                    Point position = squarePositions.get(i);
                    Color color = colors.get(i);
                    g2d.setColor(color);
                    g2d.fillRect(position.x, position.y, 7, 7);
                }
            }
            g2d.dispose();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

