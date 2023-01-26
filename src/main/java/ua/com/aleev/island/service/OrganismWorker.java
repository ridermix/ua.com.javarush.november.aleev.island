package ua.com.aleev.island.service;

import ua.com.aleev.island.entity.organism.Organism;
import ua.com.aleev.island.entity.organism.animal.Animal;
import ua.com.aleev.island.entity.map.Location;
import ua.com.aleev.island.entity.map.GameMap;
import ua.com.aleev.island.exception.GameException;

import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;

public class OrganismWorker implements Runnable {

    private final Organism prototype;
    private final GameMap gameMap;
    private final Queue<Task> tasks = new ConcurrentLinkedQueue<>();

    public OrganismWorker(Organism prototype, GameMap gameMap) {
        this.prototype = prototype;
        this.gameMap = gameMap;
    }

    @Override
    public void run() {
        Location[][] locations = gameMap.getLocations();
        for (Location[] row : locations) {
            for (Location location : row) {
                try {
                    processOneCell(location);
                } catch (Exception e) {
                    throw new GameException();
                }
            }
        }
    }

    private void processOneCell(Location location) {
        String type = prototype.getType();
        Set<Organism> organisms;
        try {
            location.getLock().lock();
            organisms = location.getResidents().get(type);
        } finally {
            location.getLock().unlock();
        }

        if (Objects.nonNull(organisms)) {
            location.getLock().lock();
            try {
                organisms.forEach(organism -> {
                    Task task = new Task(organism, o -> {
                        o.spawn(location);
                        if (organism instanceof Animal animal) {
                            animal.eat(location);
                            animal.move(location);
                        }
                        o.setWeight(o.getWeight() - o.getLimit().getMAX_WEIGHT() * 0.05);
                        if (o.getWeight() <= 0.0) {
                            o.safeDie(location);
                        }
                    });
                    tasks.add(task);
                });
            } finally {
                location.getLock().unlock();
            }
            tasks.forEach(Task::run);
            tasks.clear();
        }
    }
}
