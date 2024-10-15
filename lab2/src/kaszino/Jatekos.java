package kaszino;

abstract public class Jatekos {
    protected Asztal asztal;

    abstract public void lep();

    abstract public void setAsztal(Asztal a);

    @Override public void finalize() {
        System.out.print("Objektum azonosító: " + this.hashCode() + ", ");
        System.out.println(this.toString());
        // Tippre ez az izé azért nem csinál semmit, mert 
    }
}
