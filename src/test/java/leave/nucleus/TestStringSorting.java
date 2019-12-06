package leave.nucleus;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TestStringSorting {

    List<String> unSortedStringNumbers = null;

    @Before
    public void initialize() {
        unSortedStringNumbers = new ArrayList<String>();
        unSortedStringNumbers.add("2");
        unSortedStringNumbers.add("1");
        unSortedStringNumbers.add("3");
    }

    @Test
    public void testSortingOnStringNumbers() {
        //Collections.sort(unSortedStringNumbers, (s1, s2) -> {return s1.compareTo(s2);});
        Collections.sort(unSortedStringNumbers, Comparator.naturalOrder());
        unSortedStringNumbers.get(0).equals("1");
        unSortedStringNumbers.get(1).equals("2");
        unSortedStringNumbers.get(2).equals("3");
    }
}