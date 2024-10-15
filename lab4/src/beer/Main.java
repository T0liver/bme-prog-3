package beer;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Beer sor1 = new Beer("Soproni", "IPA", 4.2);
        Beer sor2 = new Beer("Leffe", "barna", 4.2);

        System.out.println(sor1);
        System.out.println(sor2);

        // TODO: jó encodingot beállítani (mert valhogy az UTF 8 az így nem működik erre a billenytűzetre)
        Scanner scn = new Scanner(System.in, "UTF-8");

        ArrayList<Beer> sorok = new ArrayList<>();

        sorok.add(sor1);
        sorok.add(sor2);

        while (scn.hasNextLine()) {
            String[] ln = scn.nextLine().split(" ");

            if (ln[0].equals("exit")) {
                Commands.exit(ln);
            } else if (ln[0].equals("add")) {
                Commands.add(ln, sorok);
            } else if (ln[0].equals("list")) {
                Commands.list(ln.length > 1 ? ln[1] : "", sorok);
            } else if (ln[0].equals("save")) {
                Commands.save(ln, sorok);
            } else if (ln[0].equals("load")) {
                Commands.load(ln, sorok);
            } else if (ln[0].equals("save") && ln.length > 1) {
                Commands.search(ln[1], sorok);
            } else if (ln[0].equals("find") && ln.length > 1) {
                Commands.find(ln[1], sorok);
            } else if (ln[0].equals("delete") && ln.length > 1) {
                Commands.delete(ln[1], sorok);
            }
        }

        scn.close();
    }
}