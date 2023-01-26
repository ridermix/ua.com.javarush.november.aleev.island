package ua.com.aleev.island.service;

import ua.com.aleev.island.setting.Setting;
import ua.com.aleev.island.entity.Game;
import ua.com.aleev.island.view.View;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class GameWorker extends Thread {
    private final Game game;
    private final int PERIOD = Setting.get().getPeriod();

    public GameWorker(Game game) {
        this.game = game;
    }

    @Override
    public void run() {
        View view = game.getView();
        view.showMap();
        view.showStatistics();
        ScheduledExecutorService mainPool = Executors.newScheduledThreadPool(8);

        List<OrganismWorker> workers = Arrays.stream(Setting.get()
                        .getPrototypes()).toList()
                .stream()
                .map(o -> new OrganismWorker(o, game.getGameMap()))
                .toList();
        mainPool.scheduleAtFixedRate(() -> {
            ExecutorService servicePool = Executors.newFixedThreadPool(8);
            workers.forEach(servicePool::submit);
            servicePool.shutdown();
            try {
                if (servicePool.awaitTermination(PERIOD, TimeUnit.MILLISECONDS)) {
                    view.showMap();
                    view.showStatistics();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, PERIOD, PERIOD, TimeUnit.MILLISECONDS);
    }
}
