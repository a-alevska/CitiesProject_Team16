package org.example;

import java.util.LinkedList;

public class Utils {

    private final LinkedList<String> usedCities;

    public Utils(){
        this.usedCities = new LinkedList<>();
    }

    public LinkedList<String> getUsedCities() {
        return usedCities;
    }

    public void addUsedCity(String city){
        usedCities.add(city);
    }
}
