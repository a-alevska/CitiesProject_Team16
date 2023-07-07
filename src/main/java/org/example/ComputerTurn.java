package org.example;

import java.util.Comparator;
import java.util.Random;
import java.util.stream.Collectors;

public class ComputerTurn extends Utils{

    private final UkrainianCities ukrainianCities;
    private WorldCities worldCities;
    private Utils utils = new Utils();

    public ComputerTurn(){

        this.ukrainianCities = new UkrainianCities();
        worldCities=new WorldCities();
    }

    public String generateComputerCityResponse(String city){
        String response="";
        if (!city.isEmpty()){
            response = ukrainianCities.getCities().stream()
                    .filter(c -> ukrainianCities.containsCity(city))
                    .filter(c -> !equals(city))
                    .filter(c -> !equals(utils.getUsedCities()))
                    .filter(c -> {
                        String lastLetter = city.substring(city.length() - 1).toUpperCase();
                        String secondLastChar = city.substring(city.length() - 2, city.length() - 1).toUpperCase();
                        String thirdLastChar = city.substring(city.length() - 3, city.length() - 2).toUpperCase();
                        String twoChars = city.substring(city.length() - 2).toUpperCase();
                        if(twoChars.equals("ИЙ")) {
                            return c.substring(0,1).equals(thirdLastChar);
                        }else if (lastLetter.equals("Ь")||lastLetter.equals("Й")||lastLetter.equals("И")) {
                            return c.substring(0,1).equals(secondLastChar);
                        }else{
                            return c.substring(0,1).equals(lastLetter);
                        }
                    })
                    .sorted(Comparator.comparingInt(o -> new Random().nextInt())) //рандомно сортує
                    .limit(1) //видає лише один перший елемент
                    .collect(Collectors.joining());}
        return response;
    }
    public String generateComputerWorldCityResponse(String city){
        String response="";
        if (!city.isEmpty()){
            response = worldCities.getWorldCities().stream()
                    .filter(c -> worldCities.containsCity(city))
                    .filter(c -> !equals(city))
                    .filter(c -> !equals(utils.getUsedCities()))
                    .filter(c -> {
                        String lastLetter = city.substring(city.length() - 1).toUpperCase();
                        String secondLastChar = city.substring(city.length() - 2, city.length() - 1).toUpperCase();
                        String thirdLastChar = city.substring(city.length() - 3, city.length() - 2).toUpperCase();
                        String twoChars = city.substring(city.length() - 2).toUpperCase();
                        if(twoChars.equals("ИЙ")) {
                            return c.substring(0,1).equals(thirdLastChar);
                        }else if (lastLetter.equals("Ь")||lastLetter.equals("Й")||lastLetter.equals("И")) {
                            return c.substring(0,1).equals(secondLastChar);
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
            addUsedCity(response);
            ukrainianCities.removeCity(response);
        }
        return response;
    }
    public String computerResponseWorldCity(String city) {
        String response = "";

        if (worldCities.containsCity(city) && city.length() != 0) {
            response = generateComputerWorldCityResponse(city);
            addUsedCity(response);
           worldCities.removeCity(response);
        }
        return response;
    }
}
