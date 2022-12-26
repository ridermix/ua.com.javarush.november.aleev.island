package ua.com.aleev.island.entity.organism.animal.carnivore;

import ua.com.aleev.island.annotation.OrganismTypeData;
import ua.com.aleev.island.entity.organism.Limit;

@OrganismTypeData(name = "Fox", icon = "\ud83e\udd8a", maxWeight = 8, maxCount = 30, maxSpeed = 2, maxFood = 2)
public class Fox extends Carnivore{
    public Fox(String name, String icon, double weight, Limit limit) {
        super(name, icon, weight, limit);
    }
}
