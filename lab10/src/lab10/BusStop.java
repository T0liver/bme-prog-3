package lab10;

public class BusStop {
    String name;
    String oldName;
    String wheelchair;
    boolean valid;
    double distance;

    @Override
    public String toString() {
        String ret = "";
        ret += "Megálló:\n";
        ret += "\tNév: " + (name != null ? name : "Nincs megadva");
        if (oldName != null) {
            ret += " (" + oldName + ")";
        }
        ret += "\n\tKerekesszék: " + (wheelchair != null ? wheelchair : "Nincs megadva");
        return ret;
    }
}