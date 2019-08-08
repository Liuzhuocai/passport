package elf.m.passportsimple.ui.country;

import java.util.Comparator;

/**
 * Created by jerry on 5/8/18.
 */

public class PinyinComparator implements Comparator<BaseComparatorBean> {

    public int compare(BaseComparatorBean o1, BaseComparatorBean o2) {
        if (o1.getFirst_letter().equals("@")
                || o2.getFirst_letter().equals("#")) {
            return 1;
        } else if (o1.getFirst_letter().equals("#")
                || o2.getFirst_letter().equals("@")) {
            return -1;
        } else {
            return o1.getFirst_letter().compareTo(o2.getFirst_letter());
        }
    }

}
