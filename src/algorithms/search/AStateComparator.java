package algorithms.search;

import java.util.Comparator;

public class AStateComparator implements Comparator {
    /**
     * @param o1 the first object we want to compare
     * @param o2 the second object we want to compare
     * @return 1 if the first obj is bigger,0 if they are equals, -1 if the first obj is lower and -10 if they are not AState
     */
    @Override
    public int compare(Object o1, Object o2) {
        if (o1 instanceof AState && o2 instanceof AState) {
            AState s1 = (AState) o1;
            AState s2 = (AState) o2;
            if (s1.getCost() < s2.getCost()) {
                return -1;
            } else if (s1.getCost() == s2.getCost()) {
                return 0;
            } else {
                return 1;
            }
        }
        return -10;
    }
}
