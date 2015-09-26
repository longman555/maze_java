/**
 * Permutation: JavaÇ≈ÇÃèáóÒëgçáÇπÇÃóÒãì
 * "http://iwsttty.hatenablog.com/entry/2014/03/24/011743"
 */
import java.util.Arrays;
import java.util.ArrayList;
import java.io.Serializable;

public class Permutation2<T extends Serializable> {

    private int baseIndex;
    private int index;
    private T[] objs;

    private Permutation2<T> subPermutation;

    public Permutation2(T[] objs) {
        this(0, 0, objs.clone());
    }

    private Permutation2(int baseIndex, int index, T[] objs) {
//        System.out.printf("[Permutation2] baseIndex -> %d, index -> %d\n",
//                                          baseIndex, index);
        if (objs == null || objs.length == 0) {
            throw new IllegalArgumentException();
        }

        this.baseIndex = baseIndex;
        this.index = index;
        this.objs = objs;

        if (this.index < this.objs.length - 1) {
            this.subPermutation =
                new Permutation2<T>(this.baseIndex + 1, this.index + 1, this.objs);
        }
    }

    public T[] getPermutation() { return objs; }

    public boolean next() {
        if (subPermutation == null) { return false; }

        boolean result = subPermutation.next();
        if (result == true) { return true; }

//        System.out.printf("[next] baseIndex -> %d, index -> %d\n",baseIndex,index);
        swap(baseIndex, index);

        ++index;
        if (objs.length <= index) {
            index = baseIndex;
            return false;
        }

        swap(index, baseIndex);
        return true;
    }

    @Override
    public String toString() { return Arrays.toString(objs); }

    private void swap(int index1, int index2) {
        T tmp = objs[index1];
        objs[index1] = objs[index2];
        objs[index2] = tmp;
    }

    private int permLen = -1;
    private int computePermLen(int n) {
        return (n > 1) ? (n * computePermLen(n-1)) : (1);
    }

    public int size() {
        if (permLen == -1) { permLen = computePermLen(objs.length); }
        return permLen;
    }
    public ArrayList<T[]> getAllPermutations() {
        ArrayList<T[]> result = new ArrayList<>(size());
        do {
            result.add(Arrays.copyOf(objs, objs.length));
        } while (next());
        return result;
    }

    public static void main(String[] args) {
        Permutation2<Integer> perm = new Permutation2<>(new Integer[]{1, 2, 3});
        System.out.println("3! -> " + perm.size());
        for (Integer[] p : perm.getAllPermutations()) {
            System.out.println(Arrays.toString(p));
        }
        System.out.println();

        perm = new Permutation2<>(new Integer[]{ 1, 2, 3, 4 });
        System.out.println("4! -> " + perm.size());
        for (Integer[] p : perm.getAllPermutations()) {
            System.out.println(Arrays.toString(p));
        }
        System.out.println();

        perm = new Permutation2<>(new Integer[]{ 1, 2, 3, 4, 5 });
        System.out.println("5! -> " + perm.size());
        for (Integer[] p : perm.getAllPermutations()) {
            System.out.println(Arrays.toString(p));
        }
        System.out.println();

        perm = new Permutation2<>(new Integer[]{1, 2, 3});
        do {
            System.out.println(Arrays.toString(perm.getPermutation()));
        } while (perm.next());
        System.out.println();
    }
}
