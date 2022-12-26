package ua.com.aleev.island.view;

import ua.com.aleev.island.entity.map.GameMap;
import ua.com.aleev.island.entity.map.Location;
import ua.com.aleev.island.entity.organism.Organism;
import ua.com.aleev.island.setting.Setting;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ConsoleView implements View {
    private final GameMap gameMap;
    private final int positions = Setting.getSetting().getPositionForShowInOneCell();
    private final String border = "=".repeat(positions);

    public ConsoleView(GameMap gameMap) {
        this.gameMap = gameMap;
    }


    @Override
    public String showStatistics() {
        Location[][] locations = gameMap.getLocations();
        Map<String, Integer> map = new HashMap<>();
        for (Location[] row : locations) {
            for (Location location : row) {
                Map<String, Set<Organism>> residents = location.getResidents();
                residents.values().stream()
                        .filter(s -> s.size() > 0)
                        .forEach(s -> map.put(s.stream().findAny().get().getIcon(), s.size()));
                System.out.print(map);
                map.clear();
            }
            System.out.println();

        }
        return map.toString();
    }

    @Override
    public void showGeneralStatistics() {
        Map<String, Integer> statistics = new HashMap<>();
        Location[][] locations = gameMap.getLocations();
        for (Location[] row : locations) {
            for (Location location : row) {
                Map<String, Set<Organism>> residents = location.getResidents();
                if (residents != null) {
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
        System.out.println("=".repeat(100));
        System.out.print(statistics + "\n");
        System.out.println("=".repeat(100));

    }

    @Override
    public String showMap() {
        Location[][] locations = gameMap.getLocations();
        final int cols = gameMap.getCols();
        final int rows = gameMap.getRows();
        int oneLocationWidth = positions + 1;
        int mapWidth = oneLocationWidth * cols + 2;
        StringBuilder out = new StringBuilder();
        for (int row = 0; row < rows; row++) {
            out.append(row == 0
                            ? boardLine(cols, '╔', '╦', '╗')
                            : boardLine(cols, '╠', '╬', '╣'))
                    .append("\n");
            for (int col = 0; col < cols; col++) {
                String residentString = getInstance(locations[row][col]);
                out.append(String.format("║%-" + positions + "s", residentString));
            }
            out.append('║').append("\n");
        }
        out.append(boardLine(cols, '╚', '╩', '╝')).append("\n");
        System.out.println(out);
        return out.toString();
    }

    private String getInstance(Location location) {
        return location.getResidents().values().stream()
                .filter((list) -> list.size() > 0)
                .sorted((o1, o2) -> o2.size() - o1.size())
                .limit(positions)
                .map(list -> list.stream().findAny().get().getClass().getSimpleName()
                        .substring(0, 1)).map(Object::toString).collect(Collectors.joining());
    }

    private String boardLine(int cols, char left, char center, char right) {
        return (IntStream.range(0, cols)
                .mapToObj(col -> (col == 0 ? left : center) + border)
                .collect(Collectors.joining("", "", String.valueOf(right))));
    }

}
