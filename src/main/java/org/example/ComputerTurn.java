package org.example;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Random;
import java.util.stream.Collectors;

public class ComputerTurn extends Utils{
    public ComputerTurn(){}
    public String generateComputerCityResponse(String city, LinkedList <String> listCities,LinkedList<String> usedCities){
        String response="";
        if (!city.isEmpty()){
            response = listCities.stream()
                    .filter(c -> listCities.contains(city))
                    .filter(c -> !c.equals(city))
                    .filter(c ->  !usedCities.contains(c))
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

}
