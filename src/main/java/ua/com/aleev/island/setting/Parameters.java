package ua.com.aleev.island.setting;

public class Parameters {
    private String name;
    private String icon;
    private double maxWeight;
    private double minWeight;
    private int startWeightDivisor;
    private int count;
    private int speed;
    private double foodWeight;

    public Parameters() {

    }

    public Parameters(String name, String icon, double maxWeight, double minWeight, int startWeightDivisor, int count, int speed, double foodWeight) {
        this.name = name;
        this.icon = icon;
        this.maxWeight = maxWeight;
        this.minWeight = minWeight;
        this.startWeightDivisor = startWeightDivisor;
        this.count = count;
        this.speed = speed;
        this.foodWeight = foodWeight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public double getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(double maxWeight) {
        this.maxWeight = maxWeight;
    }

    public double getMinWeight() {
        return minWeight;
    }

    public void setMinWeight(double minWeight) {
        this.minWeight = minWeight;
    }

    public int getStartWeightDivisor() {
        return startWeightDivisor;
    }

    public void setStartWeightDivisor(int startWeightDivisor) {
        this.startWeightDivisor = startWeightDivisor;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public double getFoodWeight() {
        return foodWeight;
    }

    public void setFoodWeight(double foodWeight) {
        this.foodWeight = foodWeight;
    }
}
