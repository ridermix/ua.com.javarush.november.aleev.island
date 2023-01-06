package ua.com.aleev.island.entity.organism.animal.herbivore;

import ua.com.aleev.island.constant.Sex;
import ua.com.aleev.island.entity.organism.Limit;
public class Caterpillar extends Herbivore{
    public Caterpillar(String name, String icon, double weight, Limit limit, Sex sex) {
        super(name, icon, weight, limit, sex);
    }
}
