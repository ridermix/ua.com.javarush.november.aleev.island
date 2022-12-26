package ua.com.aleev.island.entity.organism.animal.herbivore;

import ua.com.aleev.island.annotation.OrganismTypeData;
import ua.com.aleev.island.entity.organism.Limit;

@OrganismTypeData(name = "Deer", icon = "\ud83e\udd8c", maxWeight = 300, maxCount = 20, maxSpeed = 4, maxFood = 50)
public class Deer extends Herbivore{
    public Deer(String name, String icon, double weight, Limit limit) {
        super(name, icon, weight, limit);
    }
}
