package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;


public class UkrainianFlagAnimation extends JPanel implements ActionListener {
    private static final int WIDTH = 500;
    private static final int HEIGHT = 400;
    private static final int FLAG_SIZE = 50;
    private static final int TIMER_DELAY = 20;

    private final UkraineFlag[] flags;

    public UkrainianFlagAnimation() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        flags = new UkraineFlag[20];

        Random random = new Random();
        for (int i = 0; i < flags.length; i++) {
            int x = random.nextInt(WIDTH);
            int y = random.nextInt(HEIGHT);
            int speed=1+random.nextInt(2);

            flags[i] = new UkraineFlag(x, y, FLAG_SIZE /2, Color.BLUE, new Color(255, 210, 0), speed);
        }

        Timer timer = new Timer(TIMER_DELAY, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (UkraineFlag c : flags) {
            c.draw(g);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Оновлюємо позицію конфеті
        for (UkraineFlag c : flags) {
            c.update();
        }
        repaint();

    }

    private static class UkraineFlag {
        private final int x;
        private int y;
        private final int stripeHeight;
        private final Color topStripeColor;
        private final Color bottomStripeColor;
        private final int speed;

        public UkraineFlag(int x, int y, int stripeHeight, Color topStripeColor, Color bottomStripeColor, int speed) {
            this.x = x;
            this.y = y;
            this.stripeHeight = stripeHeight;
            this.topStripeColor = topStripeColor;
            this.bottomStripeColor = bottomStripeColor;
            this.speed=speed;
        }

        public void update() {
            y+=speed;

            if (y > HEIGHT) {
                y = -stripeHeight;
            }
        }

        public void draw(Graphics g) {
            g.setColor(topStripeColor);
            g.fillRect(x, y, FLAG_SIZE, stripeHeight / 2);

            g.setColor(bottomStripeColor);
            g.fillRect(x, y + stripeHeight / 2, FLAG_SIZE, stripeHeight / 2);
        }
    }

}