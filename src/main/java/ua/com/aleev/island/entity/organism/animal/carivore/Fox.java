package ua.com.aleev.island.entity.organism.animal.carivore;

import ua.com.aleev.island.constant.Sex;
import ua.com.aleev.island.entity.organism.Limit;

public class Fox extends Carnivore {
    public Fox(String name, String icon, double weight, Limit limit, Sex sex) {
        super(name, icon, weight, limit, sex);
    }
}
