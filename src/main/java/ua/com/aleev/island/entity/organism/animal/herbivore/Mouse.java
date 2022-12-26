package ua.com.aleev.island.entity.organism.animal.herbivore;

import ua.com.aleev.island.annotation.OrganismTypeData;
import ua.com.aleev.island.entity.organism.Limit;

@OrganismTypeData(name = "Mouse", icon = "\ud83d\udc2d", maxWeight = 0.05, maxCount = 500, maxSpeed = 1, maxFood = 0.01)
public class Mouse extends Herbivore{
    public Mouse(String name, String icon, double weight, Limit limit) {
        super(name, icon, weight, limit);
    }
}
