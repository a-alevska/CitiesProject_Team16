package org.example.logic;

import org.example.collections.UkrainianCities;
import org.example.collections.WorldCities;
import org.example.ui.GameResultWindow;
import org.example.ui.GameResultWindowWorld;
import org.example.ui.GameWindow;
import org.example.ui.WelcomeWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.Objects;

public class  UserTurn extends UsedCities {
    private final GameWindow window;
    private final UkrainianCities ukrainianCities;
    private final WorldCities worldCities;
    private final Timer timer;
    private int time;
    private final UsedCities usedCities = new UsedCities();
    private final ComputerTurn computerTurn;

    public static int pointCounter = 1;

    public UserTurn() {
        computerTurn = new ComputerTurn();
        window = new GameWindow();
        ukrainianCities = new UkrainianCities();
        worldCities = new WorldCities();

        timer = new Timer(1000, e -> {
            time--;
            int minutes = time / 60;
            int seconds = time % 60;
            String formattedTime = String.format("%02d:%02d", minutes, seconds);
            if (time <= 10) {
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
        setGameMode("Українські міста");
        JLabel computerResponseLabel = window.getComputerResponseLabel();

        window.getMakeMoveButton().addActionListener(e -> {
            String city = window.getCityTextField().getText().trim();
            city = computerTurn.capitalizeFirstLetter(city);
            if (city.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Введіть назву міста.", "Помилка", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (usedCities.getUsedCity().contains(city)) {
                JOptionPane.showMessageDialog(null, "Це місто вже було використано. Введіть інше місто.", "Помилка", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!ukrainianCities.containsCity(city) && !isLoser(city)) {
                JOptionPane.showMessageDialog(null, "Такого міста не існує в Україні.", "Помилка", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String lastResponse = computerResponseLabel.getText();
            if (!computerTurn.isCityValid(city, lastResponse) && !isLoser(city) && (lastResponse.length() > " Комп'ютер: ".length())) {
                JOptionPane.showMessageDialog(null, "Введіть місто, що починається на останню (чи попередню) букву відповіді комп'ютера!", "Помилка", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (isLoser(city)) {
                String message = " Ви програли \n " + "Кількість вгаданних міст: " + (pointCounter - 1);
                window.dispose();
                JOptionPane.showMessageDialog(null, message, "Гра закінчена", JOptionPane.INFORMATION_MESSAGE);
                new WelcomeWindow();

            }
            if (computerTurn.generateComputerCityResponse(city, ukrainianCities.getUkrainianCityNames(), usedCities.getUsedCity()).isEmpty() && !isLoser(city) || pointCounter > 9) {
                String message = "Кількість вгаданних міст: " + (pointCounter);
                window.dispose();
                JOptionPane.showMessageDialog(null, message, "Гра закінчена", JOptionPane.INFORMATION_MESSAGE);
                SwingUtilities.invokeLater(GameResultWindow::new);
            }
            String computerResponse = computerTurn.generateComputerCityResponse(city, ukrainianCities.getUkrainianCityNames(), usedCities.getUsedCity());
            usedCities.addUsedCity(city);
            usedCities.addUsedCity(computerResponse);
            window.getCityTextField().setText("");
            computerResponseLabel.setText(" Комп'ютер: " + computerResponse);
            setPointCounter();
        });
        window.getModeComboBox().addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                String selectedMode = (String) window.getModeComboBox().getSelectedItem();
                if (Objects.requireNonNull(selectedMode).equals("Міста всього світу")) {
                    timer.stop();
                    window.dispose();
                    new UserTurn().makeMoveWorld();
                }
            }
        });
    }

    public void makeMoveWorld() {
        restartGame();
        setGameMode("Міста всього світу");
        JLabel computerResponseLabel = window.getComputerResponseLabel();
        window.getMakeMoveButton().addActionListener(e -> {
            String city = window.getCityTextField().getText().trim();
            city = computerTurn.capitalizeFirstLetter(city);
            if (city.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Введіть назву міста.", "Помилка", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (usedCities.getUsedCity().contains(city)) {
                JOptionPane.showMessageDialog(null, "Це місто вже було використано. Введіть інше місто.", "Помилка", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!worldCities.containsCity(city) && !isLoser(city)) {
                JOptionPane.showMessageDialog(null, "Дане місто не знайдено у світовій базі даних.", "Помилка", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String lastResponse = computerResponseLabel.getText();
            if (!computerTurn.isCityValid(city, lastResponse) && !isLoser(city) && (lastResponse.length() > " Комп'ютер: ".length())) {
                JOptionPane.showMessageDialog(null, "Введіть місто, що починається на останню (чи попередню) букву відповіді комп'ютера!", "Помилка", JOptionPane.ERROR_MESSAGE);
                return;
            }
            usedCities.addUsedCity(city);

            if (isLoser(city)) {
                String message = " Ви програли \n " + "Кількість вгаданних міст: " + (pointCounter - 1);
                window.dispose();
                JOptionPane.showMessageDialog(null, message, "Гра закінчена", JOptionPane.INFORMATION_MESSAGE);
                new WelcomeWindow();
            }
            if (computerTurn.generateComputerCityResponse(city, worldCities.getWorldCityNames(), usedCities.getUsedCity()).length() == 0 && !isLoser(city) || pointCounter > 9) {
                String message = "Кількість вгаданних міст: " + (pointCounter);
                window.dispose();
                JOptionPane.showMessageDialog(null, message, "Гра закінчена", JOptionPane.INFORMATION_MESSAGE);
                SwingUtilities.invokeLater(GameResultWindowWorld::new);
            }
            String computerResponse = computerTurn.generateComputerCityResponse(city, worldCities.getWorldCityNames(), usedCities.getUsedCity());
            usedCities.addUsedCity(computerResponse);
            window.getCityTextField().setText("");
            computerResponseLabel.setText(" Комп'ютер: " + computerResponse);
            setPointCounter();
        });
        window.getModeComboBox().addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                String selectedMode = (String) window.getModeComboBox().getSelectedItem();
                if (Objects.requireNonNull(selectedMode).equals("Українські міста")) {
                    timer.stop();
                    window.dispose();
                    new UserTurn().makeMove();

                }
            }
        });
    }

    public boolean isLoser(String city) {
        return city.equalsIgnoreCase("здаюсь");
    }

    private void endGame() {
        timer.stop();
        JOptionPane.showMessageDialog(window, "Гра завершена!\nВаш рахунок: " + pointCounter, "Кінець гри", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    public void setPointCounter() {
        pointCounter++;
    }

    public void restartGame() {
        time = 300;
        timer.start();
        pointCounter = 1;
        getUsedCity().clear();
    }

    private void setGameMode(String mode) {
        JComboBox<String> modeComboBox = window.getModeComboBox();
        modeComboBox.setSelectedItem(mode);
    }

}
