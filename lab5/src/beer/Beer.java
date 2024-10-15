package beer;

import java.io.Serializable;

public class Beer implements Serializable {
    public String name;
    public String style;
    public double strength;

    public Beer(String name, String style, double strength) {
        this.name = name;
        this.style = style;
        this.strength = strength;
    }

    public String getName() {
        return name;
    }

    public String getStyle() {
        return this.style;
    }

    public double getStrength() {
        return this.strength;
    }

    @Override
    public String toString() {
        return name + "\t" + style + ",\t" + strength + "%";
    }
}
