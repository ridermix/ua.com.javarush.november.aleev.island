package ua.com.aleev.island.entity.organism.animal.herbivore;

import ua.com.aleev.island.constant.Sex;
import ua.com.aleev.island.entity.organism.Limit;
import ua.com.aleev.island.entity.organism.animal.Animal;

public abstract class Herbivore extends Animal {
    public Herbivore(String name, String icon, double weight, Limit limit, Sex sex) {
        super(name, icon, weight, limit, sex);
    }
}
