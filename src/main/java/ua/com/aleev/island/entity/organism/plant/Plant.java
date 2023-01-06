package ua.com.aleev.island.entity.organism.plant;

import ua.com.aleev.island.entity.organism.Limit;
import ua.com.aleev.island.entity.organism.Organism;
import ua.com.aleev.island.entity.map.Location;
import ua.com.aleev.island.util.Randomizer;

import java.util.Set;

public class Plant extends Organism {
    private final static Limit limit;

    static {
        limit = new Limit(1.0, 0.0, 200, 0, 0.0);
    }

    public Plant() {
        super("Plant", "\uD83C\uDF3F", limit.getMAX_WEIGHT(), limit);
    }

    public Plant(String name, String icon, double weight, Limit limit) {
        super(name, icon, weight, limit);
    }


    @Override
    public void spawn(Location currentLocation) {
        currentLocation.getLock().lock();
        try {
            Set<Organism> plants = currentLocation.getResidents().get(getType());
            if (plants.size() < getLimit().getCOUNT_ON_CELL() &&
                    getWeight() > getLimit().getMAX_WEIGHT() / 2
            ) {
                double plantRatio = (double) (plants.size()) / getLimit().getCOUNT_ON_CELL();
                if (Double.compare(plantRatio, 0.2d) <= 0) {
                    plants.add(new Plant());
                } else if (Double.compare(plantRatio, 0.5d) <= 0) {
                    newProbablyPlant(50, plants);
                } else if (Double.compare(plantRatio, 0.8d) <= 0) {
                    newProbablyPlant(10, plants);
                }
            }
        } finally {
            currentLocation.getLock().unlock();
        }
    }

    private void newProbablyPlant(int probably, Set<Organism> plants) {
        if (Randomizer.get(probably)) {
            plants.add(new Plant());
        }
    }
}
