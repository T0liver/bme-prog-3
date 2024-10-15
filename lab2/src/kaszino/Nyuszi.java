package kaszino;

public class Nyuszi extends Jatekos {
    public String szin;

    public Nyuszi(String sz) {
        szin = sz;
    }

    @Override public void lep() {
        System.out.print(szin + " " + asztal.getKor() + ". körnél járunk, és ");
        if(asztal.getTet() < 50.0) {
            asztal.emel(5);
            System.out.print("emeltem 5-tel!\n");
        } else {
            System.out.print("húha, de magas a tét, ez az " + asztal.getTet() + "!\n");
        }
    }

    @Override public void setAsztal(Asztal a) {
        this.asztal = a;
    }

    @Override public String toString() {
        return szin;
    }
}
