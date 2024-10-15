package kaszino;

public class NincsJatekos extends Exception {
    public NincsJatekos(String m) {
        super(m);
    }

    public NincsJatekos() {
        super("Nincs játékosunk!\n");
    }
}