package kaszino;

public class Robot extends Jatekos {

    private static int osid;
    private int id;

    public Robot() {
        id = ++osid;
    }

    @Override public void setAsztal(Asztal a) {
        this.asztal = a;
    }

    @Override public void lep() {
        System.out.print(this.toString() + ": " + asztal.getKor() + ". körnél járunk.\n");
    }

    @Override public String toString() {
        return "Robot" + id;
    }
}
