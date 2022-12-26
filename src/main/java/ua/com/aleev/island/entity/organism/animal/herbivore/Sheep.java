package ua.com.aleev.island.entity.organism.animal.herbivore;

import ua.com.aleev.island.annotation.OrganismTypeData;
import ua.com.aleev.island.entity.organism.Limit;

@OrganismTypeData(name = "Sheep", icon = "\ud83d\udc11", maxWeight = 70, maxCount = 140, maxSpeed = 3, maxFood = 15)
public class Sheep extends Herbivore{
    public Sheep(String name, String icon, double weight, Limit limit) {
        super(name, icon, weight, limit);
    }
}
