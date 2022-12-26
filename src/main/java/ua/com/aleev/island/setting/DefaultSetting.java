package ua.com.aleev.island.setting;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class DefaultSetting {
    //General setting
    //Island setting
    public static final int ROWS = 5;
    public static final int COLS = 5;

    public static final int PERIOD = 500;
    public static final int PERCENT_ANIMAL_SLIM = 10;

    //Organisms settings
    @JsonIgnore
    static final String[] NAMES = {
            "Wolf", "Horse", "Duck", "Caterpillar",
            "Plant",
    };
    @JsonIgnore
    static final int[][] setProbablyTable = {
            {0, 0, 0, 0, 0, 10, 15, 60, 80, 60, 70, 15, 10, 40, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 100},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 90, 100},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 100},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
    };
}