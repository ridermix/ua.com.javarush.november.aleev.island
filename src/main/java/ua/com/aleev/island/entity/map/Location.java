package ua.com.aleev.island.entity.map;

import ua.com.aleev.island.entity.organism.Organism;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Location {
    private final int col;
    private final int row;

    private final Lock lock = new ReentrantLock(true);

    public Lock getLock() {
        return lock;
    }

    private final Map<String, Set<Organism>> residents;
    private final List<Location> neighboringLocations = new ArrayList<>();

    public Location(int col, int row, Map<String, Set<Organism>> residents) {
        this.col = col;
        this.row = row;
        this.residents = residents;
    }

    public Map<String, Set<Organism>> getResidents() {
        return residents;
    }

    public void updateNeighbors(GameMap gameMap) {
        Location[][] mapLocations = gameMap.getLocations();
        Location locationToAdd;
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i >= 0 && j >= 0 && i < gameMap.HEIGHT && j < gameMap.WIDTH) {
                    locationToAdd = mapLocations[i][j];
                    if (!this.equals(locationToAdd)) {
                        neighboringLocations.add(locationToAdd);
                    }
                }
            }
        }
    }

    public List<Location> getNeighboringLocations() {
        return neighboringLocations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return col == location.col && row == location.row && Objects.equals(lock, location.lock)
                && Objects.equals(residents, location.residents)
                && Objects.equals(neighboringLocations, location.neighboringLocations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(col, row, lock, residents, neighboringLocations);
    }
}
