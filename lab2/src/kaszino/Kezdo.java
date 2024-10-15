package kaszino;

public class Kezdo extends Jatekos {
    private String nev;

    public Kezdo(String n) {
        nev = n;
    }

    @Override public void setAsztal(Asztal a) {
        this.asztal = a;
    }

    @Override public void lep() {
        System.out.print(this.toString() + ": " + asztal.getKor() + ". körnél járunk.");
        if (asztal.getKor() % 2 == 1) {
            System.out.print(" Emelek!\n");
            asztal.emel(1);
        } else {
            System.out.print(" Passz.\n");
        }
    }

    public String toString() {
        return nev;
    }
}