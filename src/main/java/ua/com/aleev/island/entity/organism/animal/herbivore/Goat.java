package ua.com.aleev.island.entity.organism.animal.herbivore;

import ua.com.aleev.island.annotation.OrganismTypeData;
import ua.com.aleev.island.entity.organism.Limit;

@OrganismTypeData(name = "Goat", icon = "\ud83d\udc10", maxWeight = 60, maxCount = 140, maxSpeed = 3, maxFood = 10)
public class Goat extends Herbivore{
    public Goat(String name, String icon, double weight, Limit limit) {
        super(name, icon, weight, limit);
    }
}
