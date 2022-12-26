package ua.com.aleev.island.entity.organism.animal.herbivore;

import ua.com.aleev.island.annotation.OrganismTypeData;
import ua.com.aleev.island.entity.organism.Limit;

@OrganismTypeData(name = "Buffalo", icon = "\ud83d\udc03", maxWeight = 700, maxCount = 10, maxSpeed = 3, maxFood = 100)
public class Buffalo extends Herbivore{
    public Buffalo(String name, String icon, double weight, Limit limit) {
        super(name, icon, weight, limit);
    }
}
