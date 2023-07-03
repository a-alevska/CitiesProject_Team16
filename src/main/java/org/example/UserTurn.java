package org.example;

import javax.swing.*;
import java.util.HashSet;

public class UserTurn implements Utils{

    private final GameWindow window;
    private final UkrainianCities ukrainianCities;
    private final HashSet<String> usedCities;
    private final int pointCounter;

    public UserTurn(){
        this.window = new GameWindow();
        this.ukrainianCities = new UkrainianCities();
        this.usedCities=new HashSet<>();
        this.pointCounter=1;
    }

    public void addUsedCity(String city) {
        usedCities.add(city);
    }

    public boolean isLoser (String city){
        return city.equalsIgnoreCase("здаюсь");
    }

    public void makeMove() {
        JLabel computerResponseLabel = window.getComputerResponseLabel();

        window.getMakeMoveButton().addActionListener(e -> {
            String city = window.getCityTextField().getText().trim();
            if (city.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Введіть назву міста.", "Помилка", JOptionPane.ERROR_MESSAGE);
                return;}
            if (usedCities.contains(city)) {
                JOptionPane.showMessageDialog(null, "Це місто вже було використано. Введіть інше місто.", "Помилка", JOptionPane.ERROR_MESSAGE);
                return;}
            if (!usedCities.contains(city)&&!ukrainianCities.containsCity(city)&&!isLoser(city)) {
                JOptionPane.showMessageDialog(null, "Такого міста не існує в Україні.", "Помилка", JOptionPane.ERROR_MESSAGE);
                return;}

            if (isLoser(city)) {
                String message = " Ви програли \n " +
                        "Кількість вгаданних міст: " + (pointCounter-1);
                JOptionPane.showMessageDialog(null, message, "Гра закінчена", JOptionPane.INFORMATION_MESSAGE);
                window.dispose();
            } if (new ComputerTurn().generateComputerCityResponse(city).length() == 0) {
                String message="Кількість вгаданних міст: " + pointCounter;
                JOptionPane.showMessageDialog(null, message, "Гра закінчена", JOptionPane.INFORMATION_MESSAGE);
                window.dispose();
            }

            window.getCityTextField().setText("");
            String computerResponse = new ComputerTurn().computerResponse((city));

            computerResponseLabel.setText(" Комп'ютер: " + computerResponse);
        });
    }


}
