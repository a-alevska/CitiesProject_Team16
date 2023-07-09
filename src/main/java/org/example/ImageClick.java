package org.example;


import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.*;

public class ImageClick extends JPanel {

    private List<Point> squarePositions = new ArrayList<>(); // Список позицій квадратиків
    private int enterCount = 0; // Лічильник натискань клавіші Enter
    private int counter = 1;

    public ImageClick() {
        setPreferredSize(new Dimension(500, 290));
        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER ) {
                    enterCount++;
                    Collections.shuffle(squarePositions); // Перемішування списку
                    repaint(); // Оновити панель, щоб відобразити перемішані квадрати

                    int count = enterCount * 3;
                    count = Math.min(count, squarePositions.size());
                    counter++;

                    int delay = 500; // Затримка між появою квадратів (у мілісекундах)
                    Timer timer = new Timer(delay, new ActionListener() {
                        private int squareIndex = 0;

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (squareIndex < 50) {
                                Point position = squarePositions.get(squareIndex);
                                squareIndex++;
                                repaint(); // Оновити панель, щоб відобразити наступний квадрат
                            } else {
                                ((Timer) e.getSource()).stop(); // Зупинити таймер, якщо всі квадрати відображені
                            }
                        }
                    });

                    timer.start(); // Запуск таймера
                }
            }
        });


        generateSquarePositions(442,230);

        generateSquarePositions(450,230);

        generateSquarePositions(458,230);

        generateSquarePositions(355,240);

        generateSquarePositions(375,240);

        generateSquarePositions(280,240);

        generateSquarePositions(122,230);

        generateSquarePositions(185,170);

        generateSquarePositions(21,290);

        generateSquarePositions(28,290);
        generateSquarePositions(36,290);

    }

    private void generateSquarePositions(int startX, int startY) {
        int spacing = 20;
        for (int i = 0; i < 5; i++) {
            int x = startX;
            int y = startY - i * spacing;
            squarePositions.add(new Point(x, y));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        ImageIcon imageIcon = new ImageIcon("logo.png");
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
            if (i < enterCount * 3) {
                Point position = squarePositions.get(i);
                g.fillRect(position.x, position.y, 8, 8);
            }
        }
    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame();
            frame.setTitle("Image Click");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            ImageClick imageClick = new ImageClick();
            frame.add(imageClick);

            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}

