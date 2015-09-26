/**
 * —^‚¦‚ç‚ê‚½”z—ñ‚©‚ç‚·‚×‚Ä‚Ì‰Â”\‚È‡—ñ‚ğ¶¬‚·‚é‘f–p‚È•û–@
 */
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.ArrayList;

class Permutation3<T extends Comparable<T>> {

    private T[] src;
    private int srcLen;
    private int permLen;

    private int factorial(int n) {
        return (n > 1) ? (n * factorial(n-1)) : (1);
    }

    public int size() { return permLen; }

    public Permutation3(T[] src) {
        this.src = Arrays.copyOf(src, src.length);
        this.srcLen = src.length;
        this.permLen = factorial(srcLen);
    }

    private void getAllPermutationsHelper(int index, boolean[] occupied, T[] dst, ArrayList<T[]> result) {
        if (index == srcLen) {
            result.add(Arrays.copyOf(dst, srcLen));
            return;
        }
        for (int i = 0; i < srcLen; ++i) {
            if (occupied[i]) { continue; }
            dst[index] = src[i];
            occupied[i] = true;
            getAllPermutationsHelper(index+1, occupied, dst, result);
            occupied[i] = false;
        }
    }

    public ArrayList<T[]> getAllPermutations() {
        ArrayList<T[]> result = new ArrayList<>(permLen);
        getAllPermutationsHelper(0, new boolean[srcLen],
                                 (T[])Array.newInstance(src.getClass().getComponentType(),srcLen),
                                 result);
        return result;
    }

    public static void main(String[] args) {
        Permutation3<Integer> perm = new Permutation3<>(new Integer[]{1, 2, 3});
        System.out.println("3! -> " + perm.size());
        for (Integer[] p : perm.getAllPermutations()) {
            System.out.println(Arrays.toString(p));
        }
        System.out.println();

        perm = new Permutation3<>(new Integer[]{ 1, 2, 3, 4 });
        System.out.println("4! -> " + perm.size());
        for (Integer[] p : perm.getAllPermutations()) {
            System.out.println(Arrays.toString(p));
        }
        System.out.println();

        perm = new Permutation3<>(new Integer[]{ 1, 2, 3, 4, 5 });
        System.out.println("5! -> " + perm.size());
        for (Integer[] p : perm.getAllPermutations()) {
            System.out.println(Arrays.toString(p));
        }
        System.out.println();
    }
}
