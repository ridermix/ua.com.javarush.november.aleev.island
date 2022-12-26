package ua.com.aleev.island;

import ua.com.aleev.island.entity.Game;
import ua.com.aleev.island.entity.map.GameMap;
import ua.com.aleev.island.factory.EntityFactory;
import ua.com.aleev.island.factory.Factory;
import ua.com.aleev.island.factory.GameMapCreator;
import ua.com.aleev.island.service.GameWorker;
import ua.com.aleev.island.setting.Setting;
import ua.com.aleev.island.view.ConsoleView;
import ua.com.aleev.island.view.View;

public class ConsoleRunner {
    public static void main(String[] args) {
        Factory entityFactory = new EntityFactory();
        GameMapCreator gameMapCreator = new GameMapCreator(entityFactory);
        int rows = Setting.getSetting().getRows();
        int cols = Setting.getSetting().getCols();
        GameMap gameMap = gameMapCreator.createRandomFilledGameMap(rows,cols);
        View view = new ConsoleView(gameMap);
        Game game = new Game(gameMap,entityFactory,view);
        GameWorker gameWorker = new GameWorker(game);
        gameWorker.start();
    }
}
