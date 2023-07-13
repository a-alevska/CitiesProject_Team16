package org.example.collections;

import java.io.*;
import java.util.LinkedList;
import java.util.Objects;

public class WorldCities {
    private final LinkedList<String>worldCities;

    public WorldCities(){
        worldCities = new LinkedList<>();
        try (InputStream inputStream = getClass().getResourceAsStream("/world_cities.txt");
             BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream)))) {
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
    public LinkedList<String> getWorldCities() {
        return worldCities;
    }
}
