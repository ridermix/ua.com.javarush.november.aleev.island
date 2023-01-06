package ua.com.aleev.island.creator;


import ua.com.aleev.island.setting.Setting;
import ua.com.aleev.island.constant.Sex;
import ua.com.aleev.island.entity.organism.Organism;
import ua.com.aleev.island.entity.organism.animal.Animal;
import ua.com.aleev.island.entity.map.Location;
import ua.com.aleev.island.entity.map.GameMap;
import ua.com.aleev.island.exception.GameException;
import ua.com.aleev.island.util.Randomizer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MapCreator {

    private final Setting setting = Setting.get();

    public GameMap createMap() {
        GameMap gameMap = new GameMap(setting.getRows(), setting.getCols());
        Location[][] locations = gameMap.getLocations();
        for (int i = 0; i < locations.length; i++) {
            for (int j = 0; j < locations[i].length; j++) {

                locations[i][j] = new Location(i, j, randomFillResidents());
            }
        }
        for (Location[] location : locations) {
            for (Location value : location) {
                value.updateNeighbors(gameMap);
            }
        }
        return gameMap;
    }

    private Map<String, Set<Organism>> randomFillResidents() {
        Map<String, Set<Organism>> residents = new HashMap<>();
        Organism[] prototypes = setting.getPrototypes();
        for (Organism prototype : prototypes) {
            String typeName = prototype.getType();
            residents.put(typeName, createOrganisms(typeName));
        }
        return residents;
    }

    private Set<Organism> createOrganisms(String type) {
        Set<Organism> organisms = new HashSet<>();
        Organism organism = null;
        for (Organism prototype : setting.getPrototypes()) {
            if (type.equalsIgnoreCase(prototype.getClass().getSimpleName())) {
                organism = prototype;
            }
        }
        if (organism != null) {
            int organismQty = organism
                    .getLimit()
                    .getCOUNT_ON_CELL();
            int qtyLimit = organismQty;
            int minQty = 0;

            for (int i = 0; i < Randomizer.random(minQty, qtyLimit); i++) {
                Organism newOrganism = Organism.replicate(organism);
                if (newOrganism instanceof Animal animal) {
                    animal.setSex(Sex.values()[Randomizer.random(0, Sex.values().length)]);
                }
                organisms.add(newOrganism);
            }
            return organisms;
        } else {
            throw new GameException("Prototype not found");
        }
    }
}
