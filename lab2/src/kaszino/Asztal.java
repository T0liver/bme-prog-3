package kaszino;

import java.util.ArrayList;
import java.util.Random;

public class Asztal {
    private ArrayList<Jatekos> jatekosok;
    private double tet;
    private int kor;
    private double goal;

    public boolean vege;

    public void ujJatek() {
        Random rnd = new Random();
        goal = rnd.nextDouble() * 100;
        jatekosok = new ArrayList<>();
        kor = 0;
        tet = 0;
        vege = false;
    }

    public void addJatekos(Jatekos j) {
        if (jatekosok.size() < 10) {
            jatekosok.add(j);
            j.setAsztal(this);
        } else {
            System.out.println("Megtelt az asztal!");
        }
    }

    public int getKor() {
        return kor;
    }

    public void emel(double d) {
        tet = tet + d;
    }

    public void kor() throws NincsJatekos {
        if (jatekosok.isEmpty()) {
            throw new NincsJatekos();
        } else {
            if (!vege) {
                for (int i = 0; i < jatekosok.size() && !vege; i++) {
                    jatekosok.get(i).lep();
                    // egyszer számoljuk ki, aztán használjuk háromszor
                    double rat = (tet / goal);
                    if (rat > 1.1) {
                        // vége van, mindenki vesztett
                        System.out.println("Mindenki vesztett! A cél ennyi volt: " + goal);
                        vege = true;
                    } else if (rat < 1.1 && rat > 1) {
                        // valaki nyert
                        System.out.println(i+1 + ". játékos nyert! A cél ennyi volt: " + goal);
                        vege = true;
                    }
                }
                if (!vege)
                    System.out.println("Vége a körnek. Aktuális tét: " + tet);
            } else {
                System.out.println("Vége a játéknak. A cél ennyi volt: " + goal);
            }
            ++kor;
        }
    }

    public double getTet() {
        return tet;
    }
}