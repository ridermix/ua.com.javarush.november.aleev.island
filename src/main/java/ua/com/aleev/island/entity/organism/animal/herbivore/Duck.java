package ua.com.aleev.island.entity.organism.animal.herbivore;

import ua.com.aleev.island.annotation.OrganismTypeData;
import ua.com.aleev.island.entity.organism.Limit;

@OrganismTypeData(name = "Duck", icon = "\uD83E\uDD86", maxWeight = 1, maxCount = 200, maxSpeed = 4, maxFood = 0.15)
public class Duck extends Herbivore {
    public Duck(String name, String icon, double weight, Limit limit) {
        super(name, icon, weight, limit);
    }

}
