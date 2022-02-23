package generics;

import java.util.Comparator;

public class SortShirtByDesc implements Comparator<Shirt> {

    @Override
    public int compare(Shirt s1, Shirt s2) {
        return s1.getDescription().compareTo(s2.getDescription());
    }
}
