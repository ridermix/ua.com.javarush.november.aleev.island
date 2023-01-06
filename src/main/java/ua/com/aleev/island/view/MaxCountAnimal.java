package ua.com.aleev.island.view;

public class MaxCountAnimal {
    private String icon;
    private int count;

    public MaxCountAnimal() {
        this.icon = "  ";
        this.count = 0;
    }

    public MaxCountAnimal(String icon, int count) {
        this.icon = icon;
        this.count = count;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
