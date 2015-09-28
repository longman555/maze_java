/*
 * 
 */
import java.util.HashSet;
import java.util.ArrayList;
public class Test {

    public static void main(String[] args) {

        Integer[] a1 = new Integer[] { 1, 2, 3, };
        Integer[] a2 = new Integer[] { 1, 2, 3, };

        System.out.println(a1 == a2);
        System.out.println(a1.equals(a2));

        ArrayList<Integer> c1 = new ArrayList<>();
        c1.add(1); c1.add(2); c1.add(3);

        ArrayList<Integer> c2 = new ArrayList<>();
        c2.add(1); c2.add(2); c2.add(3);

        System.out.println(c1 == c2);
        System.out.println(c1.equals(c2));

        ArrayList<Integer> c = new ArrayList<>();
        c.add(1); c.add(1); c.add(1);
        HashSet<Integer> hs = new HashSet<>(c);
        System.out.println(hs.size());

    }
}
