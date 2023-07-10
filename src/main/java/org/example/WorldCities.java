package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class WorldCities {
    private final LinkedList<String>worldCities;

    public WorldCities(){
        worldCities = new LinkedList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("resources/world_cities.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String cityName = line.trim();
               worldCities.add(cityName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public boolean containsCity(String city) { //метод перевіряє чи є таке місто в колекції
        return worldCities.contains(city);
    }
    public void removeCity(String city) { //видаляє місто з колекції
        worldCities.remove(city);
    }

    public LinkedList<String> getWorldCities() {
        return worldCities;
    }
}
