package ua.com.aleev.island.setting;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class DefaultSetting {
    public static final int PERIOD = 1000;
    public static final int ROWS = 4;
    public static final int COLS = 4;
    public static final Parameters[] parameters;
    @JsonIgnore
    static final String[] names = {
            "Wolf", "Boa", "Fox", "Bear", "Eagle",
            "Horse", "Deer", "Rabbit", "Mouse", "Goat", "Sheep", "Boar", "Buffalo", "Duck", "Caterpillar",
            "Plant",
    };

    static {
        parameters = new Parameters[names.length];
        for (int i = 0; i < parameters.length; i++) {
            parameters[i] = new Parameters(names[i], "", 0.0, 0.0,
                    1, 1, 0, 0.0);
        }
    }

    @JsonIgnore
    static final int[][] setProbablyTable = {
            {0, 0, 0, 0, 0, 10, 15, 60, 80, 60, 70, 15, 10, 40, 0, 0},
            {0, 0, 15, 0, 0, 0, 0, 20, 40, 0, 0, 0, 0, 10, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 70, 90, 0, 0, 0, 0, 60, 40, 0},
            {0, 80, 0, 0, 0, 40, 80, 80, 90, 70, 70, 50, 20, 10, 0, 0},
            {0, 0, 10, 0, 0, 0, 0, 90, 90, 0, 0, 0, 0, 80, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 100},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 100},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 100},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 90, 100},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 100},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 100},
            {0, 0, 0, 0, 0, 0, 0, 0, 50, 0, 0, 0, 0, 0, 90, 100},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 100},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 90, 100},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 100},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
    };
}
