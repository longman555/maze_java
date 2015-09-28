/**
 * 与えられた配列からすべての可能な順列を生成する素朴な方法
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
        @SuppressWarnings("unchecked")  // 「汎用配列」生成のため
        T[] tmp = (T[])Array.newInstance(src.getClass().getComponentType(), srcLen);
        getAllPermutationsHelper(0, new boolean[srcLen], tmp, result);
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
