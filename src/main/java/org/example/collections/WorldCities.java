package org.example.collections;

import java.io.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class WorldCities {
    private final Set<String> worldCityNames;

    public WorldCities(){
        worldCityNames = new HashSet<>();
        try (InputStream inputStream = getClass().getResourceAsStream("/world_cities.txt");
             BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String cityName = line.trim();
               worldCityNames.add(cityName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public boolean containsCity(String city) { //метод перевіряє чи є таке місто в колекції
        return worldCityNames.contains(city);
    }

    public Set<String> getWorldCityNames() {
        return worldCityNames;
    }
}
