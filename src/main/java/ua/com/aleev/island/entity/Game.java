package ua.com.aleev.island.entity;

import ua.com.aleev.island.entity.map.GameMap;
import ua.com.aleev.island.view.View;

public class Game {
    private final GameMap gameMap;
    private final View view;

    public Game(GameMap gameMap, View view) {
        this.gameMap = gameMap;
        this.view = view;
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public View getView() {
        return view;
    }
}
