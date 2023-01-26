package ua.com.aleev.island.setting;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import ua.com.aleev.island.constant.OrganismTypes;
import ua.com.aleev.island.constant.Sex;
import ua.com.aleev.island.entity.organism.Limit;
import ua.com.aleev.island.entity.organism.Organism;
import ua.com.aleev.island.entity.organism.animal.Animal;
import ua.com.aleev.island.entity.organism.plant.Plant;
import ua.com.aleev.island.exception.GameException;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class Setting implements Serializable {
    public static final String YAML_FILE = "configuration.yaml";
    private static volatile Setting SETTING;

    public static Setting get() {
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

    @JsonIgnore
    private Organism[] prototypes;
    private int period;
    private int rows;
    private int cols;
    private Parameters[] parameters;
    private final Map<String, Map<String, Integer>> probabilityFoodMap = new LinkedHashMap<>();

    public Map<String, Integer> getFoodMap(String keyName) {
        this.probabilityFoodMap.putIfAbsent(keyName, new LinkedHashMap<>());
        return probabilityFoodMap.get(keyName);
    }

    private Setting() {
        loadFromDefault();
        updateFromYaml();
    }

    private void loadFromDefault() {
        period = DefaultSetting.PERIOD;
        rows = DefaultSetting.ROWS;
        cols = DefaultSetting.COLS;
        parameters = DefaultSetting.parameters;
        this.prototypes = createPrototypes();
        for (int i = 0, n = DefaultSetting.names.length; i < n; i++) {
            String key = DefaultSetting.names[i];
            this.probabilityFoodMap.putIfAbsent(key, new LinkedHashMap<>());
            for (int j = 0; j < n; j++) {
                int ratio = DefaultSetting.setProbablyTable[i][j];
                if (ratio > 0) {
                    this.probabilityFoodMap.get(key).put(DefaultSetting.names[j], ratio);
                }
            }
        }
    }

    private void updateFromYaml() {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        ObjectReader readerForUpdating = mapper.readerForUpdating(this);
        URL resource = Setting.class.getClassLoader().getResource(YAML_FILE);
        if (Objects.nonNull(resource)) {
            try {
                readerForUpdating.readValue(resource.openStream());
                this.prototypes = createPrototypes();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public int getPeriod() {
        return period;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public Organism[] getPrototypes() {
        return prototypes;
    }

    public Map<String, Map<String, Integer>> getProbabilityFoodMap() {
        return probabilityFoodMap;
    }

    public Parameters[] getParameters() {
        return parameters;
    }

    private Organism[] createPrototypes() {
        Organism[] organisms = new Organism[OrganismTypes.values().length];
        int index = 0;
        for (OrganismTypes type : OrganismTypes.values()) {
            for (Parameters parameter : parameters) {
                if (type.getClazz().getSimpleName().equals(parameter.getName())) {
                    String name = parameter.getName();
                    String icon = parameter.getIcon();
                    Limit limit = new Limit(
                            parameter.getMaxWeight(),
                            parameter.getMinWeight(),
                            parameter.getCount(),
                            parameter.getSpeed(),
                            parameter.getFoodWeight()
                    );
                    double weight = limit.getMAX_WEIGHT() / parameter.getStartWeightDivisor();
                    organisms[index++] = generatePrototype(type.getClazz(), name, icon, weight, limit);
                }
            }
        }
        return organisms;
    }

    private Organism generatePrototype(Class<?> type, String name, String icon, double weight, Limit limit) {
        try {
            Constructor<?> constructor;
            if ("Plant".equals(type.getSimpleName())) {
                constructor = type.getConstructor(String.class, String.class, double.class, Limit.class);
                return (Plant) constructor.newInstance(name, icon, weight, limit);
            } else {
                constructor = type.getConstructor(String.class, String.class, double.class, Limit.class, Sex.class);
                return (Animal) constructor.newInstance(name, icon, weight, limit, Sex.FEMALE);
            }
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            throw new GameException("Entity constructor not found ", e);
        }
    }
}
