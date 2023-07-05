package org.example;

import java.io.*;
import java.util.LinkedList;

public class UkrainianCities {
    private final LinkedList<String> cities;

    public UkrainianCities(){
        cities = new LinkedList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("cities.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String cityName = line.trim();
                cities.add(cityName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public boolean containsCity(String city) { //метод перевіряє чи є таке місто в колекції
        return cities.contains(city);
    }
    public void removeCity(String city) { //видаляє місто з колекції
        cities.remove(city);
    }
    public LinkedList<String> getCities() {
        return cities;
    }
}
