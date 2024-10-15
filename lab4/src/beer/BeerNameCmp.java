package beer;

import java.util.Comparator;

public class BeerNameCmp implements Comparator<Beer> {

    @Override
    public int compare(Beer b1, Beer b2) {
        return b1.getName().compareTo(b2.getName());
    }

}
