package kaszino;

import java.io.BufferedReader;
import java.io.InputStreamReader;;

public class Ember extends Jatekos {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    @Override public void lep() {
        System.out.print("Ennyi a jelenlegi tét: " + asztal.getTet() + ". Mennyivel emeljünk?\n");
        double emeles;
        try {
            emeles = Double.parseDouble(br.readLine());
            asztal.emel(emeles);
            System.out.print("Rendben, emeltem a tétet ennyivel: " + emeles + "!\n");
        } catch (Exception e) {
            System.err.println("Hibás bemenet! Kör kihagyása...");
        }
    }

    @Override public void setAsztal(Asztal a) {
        this.asztal = a;
    }
}
