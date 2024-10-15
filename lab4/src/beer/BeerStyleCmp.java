package beer;

import java.util.Comparator;

public class BeerStyleCmp implements Comparator<Beer> {

    @Override
    public int compare(Beer b1, Beer b2) {
        return b1.getStyle().compareTo(b2.getStyle());
    }

}
