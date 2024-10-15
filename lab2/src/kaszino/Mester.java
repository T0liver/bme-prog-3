package kaszino;

public class Mester extends Jatekos {
    private int fok;

    public Mester(int f) {
        fok = f;
    }

    @Override public void lep() {
        System.out.print(this.toString() + ".fok: " + asztal.getKor() + ". körnél járunk.\n");
        asztal.emel(asztal.getTet()*((double)fok/100));
    }

    @Override public void setAsztal(Asztal a) {
        this.asztal = a;
    }

    @Override public String toString() {
        return "Mester " + fok;
    }
}
