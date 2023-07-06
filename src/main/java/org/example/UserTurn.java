package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserTurn extends Utils{

    private final GameWindow window;
    private final UkrainianCities ukrainianCities;
    private int pointCounter;

    private final Timer timer = new Timer(1000, new ActionListener() {

        int time = 300; // Задаємо початковий час в секундах
        public void actionPerformed(ActionEvent e) {
            time--;
            int minutes = time / 60; // Кількість хвилин
            int seconds = time % 60; // Кількість секунд
            String formattedTime = String.format("%02d:%02d", minutes, seconds); // Форматування часу
            if (time == 0) {
                endGame();
            }
            window.getTimeLabel().setText("Час пішов: " + formattedTime);
        }
    });

    private final Utils utils = new Utils();
    private final ComputerTurn computerTurn = new ComputerTurn();

    public void setPointCounter(){
        pointCounter++;
    }

    public UserTurn(){
        this.window = new GameWindow();
        this.ukrainianCities = new UkrainianCities();
        this.pointCounter=1;
    }

    public GameWindow getWindow() {
        return window;
    }

    public boolean isLoser (String city){
        return city.equalsIgnoreCase("здаюсь");
    }

    public void makeMove() {
        window.setVisible(true);
        timer.start();

        JLabel computerResponseLabel = window.getComputerResponseLabel();

        window.getMakeMoveButton().addActionListener(e -> {
            String city = window.getCityTextField().getText().trim();
            if (city.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Введіть назву міста.", "Помилка", JOptionPane.ERROR_MESSAGE);
                return;}
            if (utils.getUsedCities().contains(city)) {
                JOptionPane.showMessageDialog(null, "Це місто вже було використано. Введіть інше місто.", "Помилка", JOptionPane.ERROR_MESSAGE);
                return;}
            if (!utils.getUsedCities().contains(city)&&!ukrainianCities.containsCity(city)&&!isLoser(city)) {
                JOptionPane.showMessageDialog(null, "Такого міста не існує в Україні.", "Помилка", JOptionPane.ERROR_MESSAGE);
                return;}
            String compUp = computerResponseLabel.getText().toUpperCase();
            if(!city.equals("здаюсь")&&(compUp.length() > " Комп'ютер: ".length()) && !(compUp.substring(compUp.length() - 1).equals(city.substring(0, 1)) || compUp.substring(compUp.length() - 2, compUp.length() - 1).equals(city.substring(0, 1)))){
                JOptionPane.showMessageDialog(null, "Введіть місто, що починається на останню (чи попередню) букву відповіді комп'ютера!", "Помилка", JOptionPane.ERROR_MESSAGE);
                return;}
            utils.addUsedCity(city);
            ukrainianCities.removeCity(city);

            if (isLoser(city)) {
                String message = " Ви програли \n " +
                        "Кількість вгаданних міст: " + (pointCounter-1);
                window.dispose();
                JOptionPane.showMessageDialog(null, message, "Гра закінчена", JOptionPane.INFORMATION_MESSAGE);
            } if (computerTurn.generateComputerCityResponse(city).length() == 0&&!isLoser(city)) {
                String message="Кількість вгаданних міст: " + (pointCounter);
                window.dispose();
                JOptionPane.showMessageDialog(null, message, "Гра закінчена", JOptionPane.INFORMATION_MESSAGE);
                SwingUtilities.invokeLater(GameResultWindow::new);
            }

            String computerResponse = computerTurn.computerResponse(city);
            window.getCityTextField().setText("");

            computerResponseLabel.setText(" Комп'ютер: " + computerResponse);
            setPointCounter();
        });
    }

    private void endGame() {
        timer.stop();
        JOptionPane.showMessageDialog(window, "Гра завершена!\nВаш рахунок: " + pointCounter , "Кінець гри", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }


}
