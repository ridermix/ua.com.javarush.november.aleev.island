package ua.com.aleev.island.action;

import ua.com.aleev.island.entity.map.Location;

@FunctionalInterface
public interface Eatable {
    boolean eat(Location currentLocation);
}
