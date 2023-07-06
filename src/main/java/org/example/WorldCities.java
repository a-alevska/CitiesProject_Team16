package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class WorldCities {
    private final Set<String> worldCities;

    public WorldCities(){
        worldCities = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("world_cities.txt"))) {
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

    public Set<String> getWorldCities() {
        return worldCities;
    }
}
