package ua.com.aleev.island.entity.organism.animal.carnivore;

import ua.com.aleev.island.annotation.OrganismTypeData;
import ua.com.aleev.island.entity.organism.Limit;

@OrganismTypeData(name = "Bear", icon = "\uD83D\uDC3B", maxWeight = 500, maxCount = 5, maxSpeed = 2, maxFood = 80)
public class Bear extends Carnivore{
    public Bear(String name, String icon, double weight, Limit limit) {
        super(name, icon, weight, limit);
    }
}
