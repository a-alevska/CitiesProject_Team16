package org.example;

import java.io.*;
import java.util.HashSet;

public class UkrainianCities {
    private HashSet<String> cities;

    public UkrainianCities(){
        cities = new HashSet<>();
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
    public HashSet<String> getCities() {
        return cities;
    }
}
