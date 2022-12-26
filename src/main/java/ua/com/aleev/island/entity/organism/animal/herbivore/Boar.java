package ua.com.aleev.island.entity.organism.animal.herbivore;

import ua.com.aleev.island.annotation.OrganismTypeData;
import ua.com.aleev.island.entity.organism.Limit;

@OrganismTypeData(name = "Boar", icon = "\ud83d\udc17", maxWeight = 400, maxCount = 50, maxSpeed = 2, maxFood = 50)
public class Boar extends Herbivore{
    public Boar(String name, String icon, double weight, Limit limit) {
        super(name, icon, weight, limit);
    }
}
