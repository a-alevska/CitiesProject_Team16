package org.example.collections;

import java.io.*;
import java.util.*;

public class UkrainianCities {
    private final Set<String> ukrainianCityNames;

    public UkrainianCities() {
        ukrainianCityNames = new HashSet<>();
        try (InputStream inputStream = getClass().getResourceAsStream("/cities.txt");
             BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String cityName = line.trim();
                ukrainianCityNames.add(cityName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean containsCity(String city) { //метод перевіряє чи є таке місто в колекції
        return ukrainianCityNames.contains(city);
    }

    public Set<String> getUkrainianCityNames() {
        return ukrainianCityNames;
    }
}
