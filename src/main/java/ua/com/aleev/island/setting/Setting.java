package ua.com.aleev.island.setting;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import ua.com.aleev.island.entity.organism.Organism;
import ua.com.aleev.island.entity.organism.animal.carnivore.*;
import ua.com.aleev.island.entity.organism.animal.herbivore.*;
import ua.com.aleev.island.entity.organism.plants.Plant;
import ua.com.aleev.island.exception.GameException;
import ua.com.aleev.island.factory.OrganismCreator;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class Setting {
    public static final String SETTING_YAML = "src/main/resources/config.yaml";
    private static final Class<?>[] TYPES = {
            Wolf.class, Boa.class, Fox.class, Bear.class, Eagle.class,
            Horse.class, Deer.class, Rabbit.class, Mouse.class, Goat.class, Sheep.class, Boar.class, Buffalo.class, Duck.class, Caterpillar.class,
            Plant.class
    };
    public static final Organism[] PROTOTYPES = OrganismCreator.createPrototype(TYPES);

    private static volatile Setting SETTING;

    public static Setting getSetting() {
        Setting setting = SETTING;
        if (Objects.isNull(setting)) {
            synchronized (Setting.class) {
                if (Objects.isNull(setting = SETTING)) {
                    setting = SETTING = new Setting();
                }
            }
        }
        return setting;
    }

    private int rows;
    private int cols;
    private int positionForShowInOneCell;
    private int period;
    private int percentAnimalSlim;
    private final Map<String, Map<String, Integer>> rationMap = new LinkedHashMap<>();

    public Map<String, Integer> getRationMap(String keyName) {
        this.rationMap.putIfAbsent(keyName, new LinkedHashMap<>());
        return rationMap.get(keyName);
    }

    //INIT Setting
    private Setting() {
        loadFromDefaultSetting();
        updateFromYaml();
    }

    private void loadFromDefaultSetting() {
        rows = DefaultSetting.ROWS;
        cols = DefaultSetting.COLS;
        positionForShowInOneCell = DefaultSetting.POSITIONS_FOR_SHOW_IN_ONE_CELL;

        period = DefaultSetting.PERIOD;
        percentAnimalSlim = DefaultSetting.PERCENT_ANIMAL_SLIM;
        for (int i = 0, n = DefaultSetting.NAMES.length; i < n; i++) {
            String keyName = DefaultSetting.NAMES[i];
            this.rationMap.putIfAbsent(keyName, new LinkedHashMap<>());
            for (int j = 0; j < n; j++) {
                int ratio = DefaultSetting.setProbablyTable[i][j];
                if (ratio > 0) {
                    this.rationMap.get(keyName).put(DefaultSetting.NAMES[j], ratio);
                }
            }
        }
    }

    private void updateFromYaml() {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        ObjectReader readerForUpdating = mapper.readerForUpdating(this);
        URL resourse = Setting.class.getClassLoader().getResource(SETTING_YAML);
        if (resourse != null) {
            try {
                readerForUpdating.readValue(resourse.openStream());
            } catch (IOException e) {
                throw new GameException("Setting yaml file not found");
            }
        }
    }
    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public int getPositionForShowInOneCell() {
        return positionForShowInOneCell;
    }

    public int getPeriod() {
        return period;
    }

    public int getPercentAnimalSlim() {
        return percentAnimalSlim;
    }
}
