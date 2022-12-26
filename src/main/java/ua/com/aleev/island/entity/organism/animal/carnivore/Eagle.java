package ua.com.aleev.island.entity.organism.animal.carnivore;

import ua.com.aleev.island.annotation.OrganismTypeData;
import ua.com.aleev.island.entity.organism.Limit;

@OrganismTypeData(name = "Eagle", icon = "\ud83e\udd85", maxWeight = 6, maxCount = 20, maxSpeed = 3, maxFood = 1)
public class Eagle extends Carnivore{
    public Eagle(String name, String icon, double weight, Limit limit) {
        super(name, icon, weight, limit);
    }
}
