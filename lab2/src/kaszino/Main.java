package kaszino;

public class Main {
    public static void main(String[] args) {
        Asztal negylabu = new Asztal();
        negylabu.ujJatek();
        negylabu.addJatekos(new Nyuszi("nyuszi"));
        negylabu.addJatekos(new Mester(5));
        negylabu.addJatekos(new Ember());

        try {
            for (int i = 0; i < 10 && !negylabu.vege; i++) {
                negylabu.kor();
            }
        } catch (NincsJatekos e) {
            System.err.print(e);
        }

        negylabu = null;
        System.gc();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // azért nem írt ki semmit a finalize, mert nem hagytunk időt a gc-nek futni... */
    }

}
