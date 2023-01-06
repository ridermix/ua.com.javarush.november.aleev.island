package ua.com.aleev.island.constant;

import ua.com.aleev.island.entity.organism.Organism;
import ua.com.aleev.island.entity.organism.animal.herbivore.*;
import ua.com.aleev.island.entity.organism.animal.carivore.*;
import ua.com.aleev.island.entity.organism.plant.Plant;

public enum OrganismTypes {
    PLANT(Plant.class),
    BOAR(Boar.class),
    BUFFALO(Buffalo.class),
    CATERPILLAR(Caterpillar.class),
    DEER(Deer.class),
    DUCK(Duck.class),
    GOAT(Goat.class),
    HORSE(Horse.class),
    MOUSE(Mouse.class),
    RABBIT(Rabbit.class),
    SHEEP(Sheep.class),
    BEAR(Bear.class),
    BOA(Boa.class),
    EAGLE(Eagle.class),
    FOX(Fox.class),
    WOLF(Wolf.class);

    private final Class<? extends Organism> clazz;

    OrganismTypes(Class<? extends Organism> clazz) {
        this.clazz = clazz;
    }

    public Class<? extends Organism> getClazz() {
        return clazz;
    }
}
