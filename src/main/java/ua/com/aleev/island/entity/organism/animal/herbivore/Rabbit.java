package ua.com.aleev.island.entity.organism.animal.herbivore;

import ua.com.aleev.island.annotation.OrganismTypeData;
import ua.com.aleev.island.entity.organism.Limit;

@OrganismTypeData(name = "Rabbit", icon = "\ud83d\udc07", maxWeight = 2, maxCount = 150, maxSpeed = 2, maxFood = 0.45)
public class Rabbit extends Herbivore{
    public Rabbit(String name, String icon, double weight, Limit limit) {
        super(name, icon, weight, limit);
    }
}
