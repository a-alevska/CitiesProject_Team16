package org.example;

import javax.swing.*;
import java.util.HashSet;
import java.util.LinkedList;

public class UserTurn extends Utils{

    private final GameWindow window;
    private final UkrainianCities ukrainianCities;
    private int pointCounter;

    private final Utils utils = new Utils();

    public void setPointCounter(){
        pointCounter++;
    }

    public UserTurn(){
        this.window = new GameWindow();
        this.ukrainianCities = new UkrainianCities();
        this.pointCounter=1;
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
            if (utils.getUsedCities().contains(city)) {
                JOptionPane.showMessageDialog(null, "Це місто вже було використано. Введіть інше місто.", "Помилка", JOptionPane.ERROR_MESSAGE);
                return;}
            if (!utils.getUsedCities().contains(city)&&!ukrainianCities.containsCity(city)&&!isLoser(city)) {
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
                SwingUtilities.invokeLater(GameResultWindow::new);
                window.dispose();
            }

            window.getCityTextField().setText("");
            String computerResponse = new ComputerTurn().computerResponse((city));

            computerResponseLabel.setText(" Комп'ютер: " + computerResponse);
            setPointCounter();
        });
    }


}
