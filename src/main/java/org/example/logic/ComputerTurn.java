package org.example.logic;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Random;
import java.util.stream.Collectors;

public class ComputerTurn  {

    public String generateComputerCityResponse(String city, LinkedList<String> listCities, LinkedList<String> usedCities) {
        String response = "";
        String finalCity = city.toUpperCase().replaceAll("[ЬИЙ]", "");
        if (!finalCity.isEmpty() && listCities.contains(city)) {
            response = listCities.stream()
                    .filter(c -> !c.equals(city))
                    .filter(c -> !usedCities.contains(c))
                    .filter(c -> {
                        String lastLetter = finalCity.substring(finalCity.length() - 1);
                        return c.substring(0, 1).equals(lastLetter);
                    })
                    .sorted(Comparator.comparingInt(o -> new Random().nextInt()))
                    .limit(1)
                    .collect(Collectors.joining());
        }
        return response;
    }
       public boolean isCityValid (String city, String lastResponse){
            lastResponse = lastResponse.toUpperCase().replaceAll("[ЬИЙ]", "");
            String lastLetter = lastResponse.substring(lastResponse.length() - 1);
            String firstLetter = city.substring(0, 1);
            return lastLetter.equalsIgnoreCase(firstLetter);
        }
        public String capitalizeFirstLetter (String city){
            if (!city.isEmpty()) {
                city = city.substring(0, 1).toUpperCase() + city.substring(1).toLowerCase();
            }
            return city;
        }
    }