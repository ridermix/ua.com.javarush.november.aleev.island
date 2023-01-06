package ua.com.aleev.island;

import ua.com.aleev.island.creator.MapCreator;
import ua.com.aleev.island.entity.Game;
import ua.com.aleev.island.entity.map.GameMap;
import ua.com.aleev.island.service.GameWorker;
import ua.com.aleev.island.view.ConsoleView;
import ua.com.aleev.island.view.View;

public class ConsoleRunner {
    public static void main(String[] args) {
        MapCreator mapCreator = new MapCreator();
        GameMap gameMap = mapCreator.createMap();
        View view = new ConsoleView(gameMap);
        Game game = new Game(gameMap, view);
        GameWorker gameWorker = new GameWorker(game);
        gameWorker.start();
    }
}
