package beer;

import java.util.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Main {

    private static ArrayList<Beer> beerList = new ArrayList<>();
    private static Map<String, Command> commands = new HashMap<>();
    private static Map<String, Comparator<Beer>> comps = new HashMap<>();
    private static List<String> lparams = new LinkedList<String>();

    public static void storecmd() {
        commands.put("exit", Main::exit);
        commands.put("add", Main::add);
        commands.put("list", Main::list);
        commands.put("save", Main::save);
        commands.put("load", Main::load);
        commands.put("search", Main::search);
        commands.put("find", Main::find);
        commands.put("delete", Main::delete);
    }
    

    static {
        comps.put("name", Comparator.comparing((b) -> b.getName()));
        comps.put("style", Comparator.comparing((b) -> b.getStyle()));
        comps.put("strength", Comparator.comparing((b) -> b.getStrength()));
        lparams.add("name");
        lparams.add("style");
        lparams.add("strength");
    }

    public static void main(String[] args) {
        Beer sor1 = new Beer("Soproni", "IPA", 4.2);
        Beer sor2 = new Beer("Leffe", "barna", 4.2);

        System.out.println(sor1);
        System.out.println(sor2);

        // TODO: jó encodingot beállítani (mert valhogy az UTF 8 az így nem működik erre a billenytűzetre)
        Scanner scn = new Scanner(System.in, "UTF-8");

        beerList.add(sor1);
        beerList.add(sor2);

        storecmd();

        while (scn.hasNextLine()) {
            String[] ln = scn.nextLine().split(" ");

            if(commands.containsKey(ln[0])) {
                commands.get(ln[0]).execute(ln);
            } else {
                System.out.println("Hibás vagy nem létező parancs!");
            }
        }

        scn.close();
    }

    protected static void exit(String[] args) {
        System.exit(0);
    }

    protected static void add(String[] args) {
        beerList.add(new Beer(args[1], args[2], Double.parseDouble(args[3])));
    }

    protected static void list(String[] args) {

        for (int i = 1; i < args.length; i++) {
            String param = args[i];
            if (lparams.contains(param)) {
                lparams.remove(param);
            }
            lparams.add(0, param);
        }

        Comparator<Beer> combcmp = null;
        for (String p : lparams) {
            Comparator<Beer> cmp = comps.get(p);
            if (combcmp == null) {
                combcmp = cmp;
            } else {
                combcmp = combcmp.thenComparing(cmp);
            }
        }

        beerList.sort(combcmp);

        for (Beer b : beerList) {
            System.out.println(b);
        }
    }

    protected static void save(String[] args) {
        try {
            ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream("beerlist.txt"));
            oo.writeObject(beerList);
            oo.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected static void search(String[] args) {
        String arg = args.length > 1 ? args[1] : "";
        for (Beer beer : beerList) {
            if (beer.getName().matches(arg)) {
                System.out.println(beer);
            }
        }
    }

    protected static void find(String[] args) {
        String arg = args.length > 1 ? args[1] : "";
        for (Beer beer : beerList) {
            if (beer.getName().contains(arg)) {
                System.out.println(beer);
            }
        }
    }

    protected static void delete(String[] args) {
        String arg = args.length > 1 ? args[1] : "";
		try {
			for (Beer beer : beerList) {
                if (beer.getName().matches(arg)) {
                    beerList.remove(beer);
                }
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    @SuppressWarnings("unchecked")
    protected static void load(String[] args) {
        String arg = args.length > 1 ? args[1] : "beerlist.txt";
        try {
            ObjectInputStream oi = new ObjectInputStream(new FileInputStream(arg));
            beerList = (ArrayList<Beer>) oi.readObject();
            oi.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}