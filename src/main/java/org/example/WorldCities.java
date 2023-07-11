package org.example;

import java.io.*;
import java.util.LinkedList;

public class WorldCities {
    private final LinkedList<String>worldCities;

    public WorldCities(){
        worldCities = new LinkedList<>();
        try (InputStream inputStream = getClass().getResourceAsStream("/world_cities.txt");
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
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
