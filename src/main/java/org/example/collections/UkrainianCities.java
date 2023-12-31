package org.example.collections;

import java.io.*;
import java.util.LinkedList;
import java.util.Objects;

public class UkrainianCities {
    private final LinkedList<String> cities;

    public UkrainianCities(){
        cities = new LinkedList<>();
        try (InputStream inputStream = getClass().getResourceAsStream("/cities.txt");
             BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream)))) {
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

    public LinkedList<String> getCities() {
        return cities;
    }
}
