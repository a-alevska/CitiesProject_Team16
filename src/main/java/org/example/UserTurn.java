package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.Objects;

public class UserTurn extends Utils{
    private final GameWindow window;
    private final UkrainianCities ukrainianCities;
    private final Timer timer;
    private int time;
    private final Utils utils = new Utils();
    private final ComputerTurn computerTurn;
    private final WorldCities worldCities;
   static int pointCounter=1;

    public UserTurn(){
        computerTurn = new ComputerTurn();
        this.window = new GameWindow();
        ukrainianCities = new UkrainianCities();
        worldCities=new WorldCities();
        time = 300;

        // Задаємо початковий час в секундах
        timer = new Timer(1000, e -> {
           time--;
           int minutes = time / 60; // Кількість хвилин
           int seconds = time % 60; // Кількість секунд
           String formattedTime = String.format("%02d:%02d", minutes, seconds); // Форматування часу
           if(time<=10){
               window.getTimeLabel().setForeground(Color.red);
           }
           if (time == 0) {
               endGame();
           }
           window.getTimeLabel().setText("Час пішов: " + formattedTime);
       });

    }
    public void makeMove() {
        restartGame();
        setGameMode ("Українські міста");
        JLabel computerResponseLabel = window.getComputerResponseLabel();

        window.getMakeMoveButton().addActionListener(e -> {
            String city = window.getCityTextField().getText().trim();
            if (!city.isEmpty()) {
                city = city.substring(0, 1).toUpperCase() + city.substring(1).toLowerCase();
            }
            if (city.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Введіть назву міста.", "Помилка", JOptionPane.ERROR_MESSAGE);
                return;}
            if (utils.getUsedCities().contains(city)) {
                JOptionPane.showMessageDialog(null, "Це місто вже було використано. Введіть інше місто.", "Помилка", JOptionPane.ERROR_MESSAGE);
                return;}
            if (!ukrainianCities.containsCity(city)&&!isLoser(city)) {
                JOptionPane.showMessageDialog(null, "Такого міста не існує в Україні.", "Помилка", JOptionPane.ERROR_MESSAGE);
                return;}
            String compUp = computerResponseLabel.getText().toUpperCase();
            if(!isLoser(city)&&(compUp.length() > " Комп'ютер: ".length()) && !(compUp.substring(compUp.length() - 1).equals(city.substring(0, 1)) || compUp.substring(compUp.length() - 2, compUp.length() - 1).equals(city.substring(0, 1)))){
                JOptionPane.showMessageDialog(null, "Введіть місто, що починається на останню (чи попередню) букву відповіді комп'ютера!", "Помилка", JOptionPane.ERROR_MESSAGE);
                return;}
            if (isLoser(city)) {
                String message = " Ви програли \n " +
                        "Кількість вгаданних міст: " + (pointCounter-1);
                window.dispose();
                JOptionPane.showMessageDialog(null, message, "Гра закінчена", JOptionPane.INFORMATION_MESSAGE);

            } if (computerTurn.generateComputerCityResponse(city,ukrainianCities.getCities(),utils.getUsedCities()).isEmpty()&&!isLoser(city)) {
                String message="Кількість вгаданних міст: " + (pointCounter);
                window.dispose();
                JOptionPane.showMessageDialog(null, message, "Гра закінчена", JOptionPane.INFORMATION_MESSAGE);
                SwingUtilities.invokeLater(GameResultWindow::new);
            }
            String computerResponse = computerTurn.generateComputerCityResponse(city,ukrainianCities.getCities(),utils.getUsedCities());
            utils.addUsedCity(city);
            utils.addUsedCity(computerResponse);
            window.getCityTextField().setText("");
            computerResponseLabel.setText(" Комп'ютер: " + computerResponse);
            setPointCounter();
        });
        window.getModeComboBox().addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                String selectedMode = (String)  window.getModeComboBox().getSelectedItem();
                if (Objects.requireNonNull(selectedMode).equals("Міста всього світу")) {
                    window.dispose();
                    new UserTurn().makeMoveWorld();
                }
            }
        });
    }
    public void makeMoveWorld() {
        restartGame();
        setGameMode ("Міста всього світу");
        JLabel computerResponseLabel = window.getComputerResponseLabel();
        window.getMakeMoveButton().addActionListener(e -> {
            String city = window.getCityTextField().getText().trim();
            if (!city.isEmpty()) {
                city = city.substring(0, 1).toUpperCase() + city.substring(1).toLowerCase();
            }
            if (city.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Введіть назву міста.", "Помилка", JOptionPane.ERROR_MESSAGE);
                return;}
            if (utils.getUsedCities().contains(city)) {
                JOptionPane.showMessageDialog(null, "Це місто вже було використано. Введіть інше місто.", "Помилка", JOptionPane.ERROR_MESSAGE);
                return;}
            if (!worldCities.containsCity(city)&&!isLoser(city)) {
                JOptionPane.showMessageDialog(null, "Дане місто не знайдено у світовій базі даних.", "Помилка", JOptionPane.ERROR_MESSAGE);
                return;}
            String compUp = computerResponseLabel.getText().toUpperCase();
            if(!isLoser(city)&&(compUp.length() > " Комп'ютер: ".length()) && !(compUp.substring(compUp.length() - 1).equals(city.substring(0, 1)) || compUp.substring(compUp.length() - 2, compUp.length() - 1).equals(city.substring(0, 1)))){
                JOptionPane.showMessageDialog(null, "Введіть місто, що починається на останню (чи попередню) букву відповіді комп'ютера!", "Помилка", JOptionPane.ERROR_MESSAGE);
                return;}
            utils.addUsedCity(city);

            if (isLoser(city)) {
                String message = " Ви програли \n " +
                        "Кількість вгаданних міст: " + (pointCounter-1);
                window.dispose();
                JOptionPane.showMessageDialog(null, message, "Гра закінчена", JOptionPane.INFORMATION_MESSAGE);

            } if (computerTurn.generateComputerCityResponse(city, worldCities.getWorldCities(),utils.getUsedCities()).length() == 0&&!isLoser(city)) {
                String message="Кількість вгаданних міст: " + (pointCounter);
                window.dispose();
                JOptionPane.showMessageDialog(null, message, "Гра закінчена", JOptionPane.INFORMATION_MESSAGE);
            }
            String computerResponse = computerTurn.generateComputerCityResponse(city,worldCities.getWorldCities(),utils.getUsedCities());
            utils.addUsedCity(computerResponse);
            window.getCityTextField().setText("");
            computerResponseLabel.setText(" Комп'ютер: " + computerResponse);
            setPointCounter();
        });
        window.getModeComboBox().addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                String selectedMode = (String)  window.getModeComboBox().getSelectedItem();
                if (Objects.requireNonNull(selectedMode).equals("Українські міста")) {
                    window.dispose();
                    new UserTurn().makeMove();

                }
            }
        });
    }

    public boolean isLoser (String city){
        return city.equalsIgnoreCase("здаюсь");
    }
    private void endGame() {
        timer.stop();
        JOptionPane.showMessageDialog(window, "Гра завершена!\nВаш рахунок: " + pointCounter , "Кінець гри", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }
    public void setPointCounter(){
        pointCounter++;
    }
    public void restartGame (){
        time = 300;
        timer.start();
        pointCounter=1;
        getUsedCities().clear();
    }
    private void setGameMode(String mode) {
        JComboBox<String> modeComboBox = window.getModeComboBox();
        modeComboBox.setSelectedItem(mode);
    }

}
