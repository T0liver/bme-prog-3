package beer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Commands {
    static protected void exit(String[] args) {
        System.exit(0);
    }

    static protected void add(String[] args, ArrayList<Beer> beerList) {
        beerList.add(new Beer(args[1], args[2], Double.parseDouble(args[3])));
    }

    static protected void list(String arg, ArrayList<Beer> beerList) {
        Comparator<Beer> cmp;
        switch (arg) {
            case "name":
                cmp = new BeerNameCmp();
                Collections.sort(beerList, cmp);
                break;

            case "style":
                cmp = new BeerStyleCmp();
                Collections.sort(beerList, cmp);
                break;
        
            case "strength":
                cmp = new BeerStrengthCmp();
                Collections.sort(beerList, cmp);
                break;
            default:
                break;
        }

        for (Beer b : beerList) {
            System.out.println(b);
        }
    }

    static protected void save(String[] args, ArrayList<Beer> beerList) {
        try {
            ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream("beerlist.txt"));
            oo.writeObject(beerList);
            oo.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static protected void search(String arg, ArrayList<Beer> beerList) {
        for (Beer beer : beerList) {
            if (beer.getName().matches(arg)) {
                System.out.println(beer);
            }
        }
    }

    static protected void find(String arg, ArrayList<Beer> beerList) {
        for (Beer beer : beerList) {
            if (beer.getName().contains(arg)) {
                System.out.println(beer);
            }
        }
    }

    static protected void delete(String arg, ArrayList<Beer> beerList) {
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
    static protected void load(String[] args, ArrayList<Beer> beerList) {
        try (ObjectInputStream oi = new ObjectInputStream(new FileInputStream("beerlist.txt"))) {
			beerList = (ArrayList<Beer>) oi.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
