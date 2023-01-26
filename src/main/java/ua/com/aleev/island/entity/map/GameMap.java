package ua.com.aleev.island.entity.map;

public class GameMap {
    public final int HEIGHT;
    public final int WIDTH;

    private final Location[][] locations;

    public GameMap(int HEIGHT, int WIDTH) {
        this.HEIGHT = HEIGHT;
        this.WIDTH = WIDTH;

        locations = new Location[HEIGHT][WIDTH];
    }

    public Location[][] getLocations() {
        return locations;
    }
}
