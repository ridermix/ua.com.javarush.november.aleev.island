package ua.com.aleev.island.view;

public class MaxCountOrganism {
    private String icon;
    private int count;

    public MaxCountOrganism() {
        this.icon = "  ";
        this.count = 0;
    }

    public MaxCountOrganism(String icon, int count) {
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
