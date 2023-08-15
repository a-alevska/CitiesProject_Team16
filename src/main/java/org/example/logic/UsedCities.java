package org.example.logic;


import java.util.HashSet;
import java.util.Set;

public class UsedCities {

    private final Set <String> usedCity;

    public UsedCities() {
        this.usedCity = new HashSet<>();
    }

    public Set<String> getUsedCity() {
        return usedCity;
    }

    public void addUsedCity(String city) {
        usedCity.add(city);
    }
}
