package ua.com.aleev.island.view;

import ua.com.aleev.island.entity.organism.Organism;
import ua.com.aleev.island.entity.organism.animal.herbivore.Herbivore;
import ua.com.aleev.island.entity.organism.animal.carivore.Carnivore;
import ua.com.aleev.island.entity.map.Location;
import ua.com.aleev.island.entity.map.GameMap;
import ua.com.aleev.island.entity.organism.plant.Plant;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class ConsoleView implements View {
    private final GameMap gameMap;

    public ConsoleView(GameMap gameMap) {
        this.gameMap = gameMap;
    }


    @Override
    public String showStatistics() {
        Map<String, Integer> statistics = new HashMap<>();
        Location[][] locations = gameMap.getLocations();
        for (Location[] row : locations) {
            for (Location location : row) {
                var residents = location.getResidents();
                if (Objects.nonNull(residents)) {
                    residents.values().stream()
                            .filter(set -> set.size() > 0)
                            .forEach(set -> {
                                        String icon = set.stream().findAny().get().getIcon();
                                        statistics.put(icon, statistics.getOrDefault(icon, 0) + set.size());
                                    }
                            );
                }
            }
        }
        System.out.println(statistics + "\n");
        return statistics.toString();
    }

    @Override
    public String showMap() {
        StringBuilder out = new StringBuilder("\n");
        Location[][] locations = gameMap.getLocations();
        final int cols = gameMap.WIDTH;

        final int rows = gameMap.HEIGHT;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                String residentString = get(locations[row][col]);
                int cellWidth = 4;
                out.append(String.format("║%-" + cellWidth + "s", residentString));
            }
            out.append('║').append("\n");
        }
        System.out.println(out);
        return out.toString();
    }

    private String get(Location location) {
        MaxCountOrganism herbivoreMaxCount = getMaxAnimalCount(location, Herbivore.class);
        MaxCountOrganism predatorMaxCount = getMaxAnimalCount(location, Carnivore.class);
        MaxCountOrganism plantMaxCount = getMaxAnimalCount(location, Plant.class);
        return predatorMaxCount.getIcon() + "|" + herbivoreMaxCount.getIcon() + "|" + plantMaxCount.getIcon();
    }

    private MaxCountOrganism getMaxAnimalCount(Location location, Class<?> clazz) {
        location.getLock().lock();
        MaxCountOrganism maxCountOrganism = location.getResidents().values().stream()
                .filter((list) -> list.size() > 0)
                .filter((list) -> clazz.isAssignableFrom(list.iterator().next().getClass()))
                .sorted((o1, o2) -> o2.size() - o1.size())
                .limit(1)
                .map(list -> {
                    MaxCountOrganism maxAnimal = new MaxCountOrganism("  ", 0);
                    Organism organism = list.stream().findAny().orElseThrow();
                    int maxCount = organism.getLimit().getCOUNT_ON_CELL();
                    String animalIcon = organism.getIcon();
                    maxAnimal.setIcon(animalIcon);
                    maxAnimal.setCount(list.size());
                    return maxAnimal;
                })
                .findAny().orElseGet(MaxCountOrganism::new);
        location.getLock().unlock();
        return maxCountOrganism;
    }

    private int[] getPlantCount(Location location) {
        location.getLock().lock();
        int maxCount = 0;
        Set<Organism> plants = location.getResidents().get("Plant");
        int count = plants.size();
        if (count > 0) {
            maxCount = plants
                    .stream()
                    .findAny()
                    .get()
                    .getLimit()
                    .getCOUNT_ON_CELL();
        }
        location.getLock().unlock();
        return new int[]{count, maxCount};
    }
}
