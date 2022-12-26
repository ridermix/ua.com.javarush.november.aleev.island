package ua.com.aleev.island.entity.organism.animal.carnivore;

import ua.com.aleev.island.annotation.OrganismTypeData;
import ua.com.aleev.island.entity.organism.Limit;

@OrganismTypeData(name = "Boa", icon = "\ud83d\udc0d", maxWeight = 15, maxCount = 30, maxSpeed = 1, maxFood = 3)
public class Boa extends Carnivore {

    public Boa(String name, String icon, double weight, Limit limit) {
        super(name, icon, weight, limit);
    }
}
