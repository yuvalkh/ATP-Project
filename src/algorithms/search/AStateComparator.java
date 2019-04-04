package algorithms.search;

import java.util.Comparator;

public class AStateComparator implements Comparator {
    /**
     *
     * @param o1
     * @param o2
     * @return
     */
    @Override
    public int compare(Object o1, Object o2) {
        if(o1 instanceof AState && o2 instanceof AState) {
            AState s1 = (AState) o1;
            AState s2 = (AState) o2;
            if (s1.getCost() < s2.getCost()) {
                return 0;
            } else if (s1.getCost() == s2.getCost()) {
                return 0;
            } else{
                return 1;
            }
        }
        return -10;
    }
}
