package ua.com.aleev.island.entity.organism;

import java.io.Serializable;

public class Limit implements Serializable {
    private final double MAX_WEIGHT;
    private final double MIN_WEIGHT;
    private final int COUNT_ON_CELL;
    private final int SPEED;
    private final double MAX_FOOD_WEIGHT;

    public Limit(double MAX_WEIGHT, double MIN_WEIGHT, int COUNT_ON_CELL, int SPEED, double MAX_EATING_WEIGHT) {
        this.MAX_WEIGHT = MAX_WEIGHT;
        this.MIN_WEIGHT = MIN_WEIGHT;
        this.COUNT_ON_CELL = COUNT_ON_CELL;
        this.SPEED = SPEED;
        this.MAX_FOOD_WEIGHT = MAX_EATING_WEIGHT;
    }

    public double getMAX_WEIGHT() {
        return MAX_WEIGHT;
    }

    public double getMIN_WEIGHT() {
        return MIN_WEIGHT;
    }

    public int getCOUNT_ON_CELL() {
        return COUNT_ON_CELL;
    }

    public int getSPEED() {
        return SPEED;
    }

    public double getMAX_EATING_WEIGHT() {
        return MAX_FOOD_WEIGHT;
    }
}
