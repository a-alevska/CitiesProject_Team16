package org.example;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Random;
import java.util.stream.Collectors;

public class ComputerTurn implements Utils{

    private final UkrainianCities ukrainianCities;
    private final HashSet<String> usedCities;
    private int pointCounter;

    public ComputerTurn(){
        this.ukrainianCities = new UkrainianCities();
        this.usedCities=new HashSet<>();
        this.pointCounter=1;
    }

    public void addUsedCity(String city) {
        usedCities.add(city);
    }

    public String generateComputerCityResponse(String city){
        String response="";
        if (!city.isEmpty()){
            response = ukrainianCities.getCities().stream()  //перетворює коллекцію з містами в стрім
                    .filter(c -> equals(city))
                    .filter(c -> !c.endsWith("й"))
                    .filter(c -> {
                        String lastLetter = city.substring(city.length() - 1).toUpperCase(); //знаходить останню букву
                        if (lastLetter.equals("Ь")) { //якщо остання ь то бере пердостанню
                            String secondLastChar = city.substring(city.length() - 2,city.length() - 1);
                            return c.contains(secondLastChar);
                        } else {
                            return c.contains(lastLetter);
                        }
                    })
                    .sorted(Comparator.comparingInt(o -> new Random().nextInt())) //рандомно сортує
                    .limit(1) //видає лише один перший елемент
                    .collect(Collectors.joining());}
        return response;
    }

    public String computerResponse(String city) { //метод генерує відповідь на різні ситуації
        String response = "";

        if (ukrainianCities.containsCity(city) && city.length() != 0) {
            pointCounter++;
            response = generateComputerCityResponse(city);
            addUsedCity(city);
            addUsedCity(response);
            ukrainianCities.removeCity(city);
            ukrainianCities.removeCity(response);
        }
        return response;
    }
}
