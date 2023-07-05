package org.example;

import java.util.Comparator;
import java.util.Random;
import java.util.stream.Collectors;

public class ComputerTurn extends Utils{

    private final UkrainianCities ukrainianCities;

    public ComputerTurn(){
        this.ukrainianCities = new UkrainianCities();
    }

    public String generateComputerCityResponse(String city){
        String response="";
        if (!city.isEmpty()){
            response = ukrainianCities.getCities().stream()
                    .filter(c -> !equals(city))
                    .filter(c -> {
                        String lastLetter = city.substring(city.length() - 1).toUpperCase();
                        String secondLastChar = city.substring(city.length() - 2, city.length() - 1);
                        if (lastLetter.equals("Ь")||lastLetter.equals("Й")||lastLetter.equals("И")) {
                            return c.substring(0,1).equals(secondLastChar);
                        } else if(secondLastChar.equals("Ь")||secondLastChar.equals("Й")||secondLastChar.equals("И")){
                            return c.substring(0,1).equals(city.substring(city.length() - 3, city.length() - 2));
                        }else{
                            return c.substring(0,1).equals(lastLetter);
                        }
                    })
                    .sorted(Comparator.comparingInt(o -> new Random().nextInt())) //рандомно сортує
                    .limit(1) //видає лише один перший елемент
                    .collect(Collectors.joining());}
        return response;
    }

    public String computerResponse(String city) {
        String response = "";

        if (ukrainianCities.containsCity(city) && city.length() != 0) {
            response = generateComputerCityResponse(city);
            addUsedCity(city);
            addUsedCity(response);
            ukrainianCities.removeCity(city);
            ukrainianCities.removeCity(response);
        }
        return response;
    }
}
