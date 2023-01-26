package ua.com.aleev.island.entity.organism.animal;

import ua.com.aleev.island.action.Eatable;
import ua.com.aleev.island.action.Movable;
import ua.com.aleev.island.setting.Setting;
import ua.com.aleev.island.constant.Sex;
import ua.com.aleev.island.entity.organism.Limit;
import ua.com.aleev.island.entity.organism.Organism;
import ua.com.aleev.island.entity.map.Location;
import ua.com.aleev.island.exception.GameException;
import ua.com.aleev.island.util.Randomizer;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class Animal extends Organism implements Eatable, Movable {

    private Sex sex;

    public Animal(String name, String icon, double weight, Limit limit, Sex sex) {

        super(name, icon, weight, limit);
        this.sex = sex;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    @Override
    public boolean eat(Location currentLocation) {
        currentLocation.getLock().lock();
        try {
            double needToEat = getWeightToEat();
            if (!(needToEat <= 0)) {
                Setting setting = Setting.get();
                Map<String, Integer> foodMap = setting.getFoodMap(getType());
                Set<String> residents = currentLocation.getResidents().keySet();
                Set<String> existingFoodTypes = new HashSet<>(foodMap.keySet());
                existingFoodTypes.retainAll(residents);

                for (String foodType : existingFoodTypes) {
                    int probably = foodMap.get(foodType);
                    Set<Organism> foods = currentLocation
                            .getResidents()
                            .get(foodType);
                    if (foods.size() > 0 && Randomizer.get(probably)) {
                        Organism food = foods
                                .iterator()
                                .next();
                        double weight = getWeight();
                        double foodWeight = food.getWeight();
                        double weightToEat = Math.min(foodWeight, needToEat);

                        setWeight(weight + weightToEat);
                        food.setWeight(food.getWeight() - weightToEat);
                        if (!food.isALive()) {
                            foods.remove(food);
                        }
                        return true;
                    }
                }
                return false;
            }
            return false;
        } finally {
            currentLocation.getLock().unlock();
        }
    }


    @Override
    public void move(Location startLocation) {
        int speed = LIMIT.getSPEED();
        if (speed > 0) {
            int stepsCount = Randomizer.random(0, speed);
            Location previousLocation = startLocation;
            Location destinationLocation = null;
            int maxCountOnCell = LIMIT.getCOUNT_ON_CELL();
            for (int i = 0; i < stepsCount; i++) {
                List<Location> neighborLocations = previousLocation.getNeighboringLocations();
                if (neighborLocations.size() == 1) {
                    destinationLocation = neighborLocations.get(0);
                } else if (neighborLocations.size() > 0) {
                    destinationLocation = neighborLocations.get(Randomizer.random(0, neighborLocations.size() - 1));
                }
                if (destinationLocation != null
                        && destinationLocation.getResidents().get(getType()).size() < maxCountOnCell) {
                    previousLocation = destinationLocation;
                } else {
                    destinationLocation = previousLocation;
                }
            }
            if (destinationLocation != null && !destinationLocation.equals(startLocation)) {
                if (isALive()) {
                    safeMove(startLocation, destinationLocation);
                }
            }
        }
    }

    protected void safeMove(Location source, Location destination) {
        if (safeAddTo(destination)) {
            if (!safePollFrom(source)) {
                safePollFrom(destination);
            }
        }
    }

    protected boolean safeAddTo(Location location) {
        location.getLock().lock();
        try {
            Set<Organism> set = location.getResidents().get(getType());
            int maxCount = getLimit().getCOUNT_ON_CELL();
            int size = set.size();
            return size < maxCount && set.add(this);
        } finally {
            location.getLock().unlock();
        }
    }

    protected boolean safePollFrom(Location location) {
        location.getLock().lock();
        try {
            return location.getResidents().get(getType()).remove(this);
        } finally {
            location.getLock().unlock();
        }
    }

    @Override
    public void spawn(Location currentLocation) {
        currentLocation.getLock().lock();
        try {
            double minWeightRatio = sex.equals(Sex.FEMALE) ? 0.9 : 0.5;
            if (isALive() && getWeight() > getLimit().getMAX_WEIGHT() * minWeightRatio) {
                Sex partnerSex = sex.equals(Sex.FEMALE) ? Sex.MALE : Sex.FEMALE;
                Set<Organism> partners = getPartners(currentLocation, partnerSex);
                if (partners.size() > 0) {
                    Sex childSex = Sex.values()[Randomizer.random(0, Sex.values().length - 1)];
                    Animal child = (Animal) Organism.replicate(getPrototype());
                    child.setSex(childSex);
                    if (sex.equals(Sex.FEMALE)) {
                        setWeight(getWeight() - getWeight() * 0.3);
                    }
                }
            }
        } finally {
            currentLocation.getLock().unlock();
        }
    }

    private double getWeightToEat() {
        double maxEatingWeight = LIMIT.getMAX_EATING_WEIGHT();
        double hungryWeight = LIMIT.getMAX_WEIGHT() - this.getWeight();
        return Math.min(hungryWeight, maxEatingWeight);
    }

    private Set<Organism> getPartners(Location location, Sex partnerSex) {
        double minWeightRatio = partnerSex.equals(Sex.FEMALE) ? 0.9 : 0.5;
        Set<Organism> partners;
        partners = location
                .getResidents()
                .get(getType())
                .stream()
                .map(o -> ((Animal) o))
                .filter(a -> a.isALive()
                        && partnerSex.equals(a.getSex())
                        && a.getWeight() > a.getWeight() * minWeightRatio)
                .collect(Collectors.toSet());
        return partners;
    }

    private Organism getPrototype() {
        Organism organism = null;
        for (Organism prototype : Setting.get().getPrototypes()) {
            if (getType().equalsIgnoreCase(prototype.getClass().getSimpleName())) {
                organism = prototype;
            }
        }
        if (organism != null) {
            return organism;
        } else {
            throw new GameException("Prototype not found");
        }
    }
}
