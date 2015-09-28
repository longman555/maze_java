/**
 * �^����ꂽ�z�񂩂琶���\�Ȃ��ׂĂ̏���𐶐�����.
 *
 * �g�p�@�F
 * ----------------------------------------------------------------------------
 * Permutation<Integer> perms = new Permutation(new Integer[] { 1, 2, 3 });
 * do {
 *     System.out.println(perms.toString());
 * } while (perms.next());
 * ----------------------------------------------------------------------------
 */
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashSet;

public class Permutation<T extends Comparable<T>> {

    private T[] src;
    private int srcLen;
    private int permNum;

    public Permutation(T[] src) {
        this.src = Arrays.copyOf(src, src.length);
        this.srcLen = src.length;
        this.permNum = factorial(srcLen);
    }

    public T[] getPermutation() { return src; }

    private int factorial(int n) {
        return (n > 1) ? (n * factorial(n-1)) : (1);
    }

    public int size() { return permNum; }

    private void swap(int lhs, int rhs) {
        T tmp = src[lhs];
        src[lhs] = src[rhs];
        src[rhs] = tmp;
    }

    private void reverse(int start, int end) {
        int n = (end-start) / 2;
        for (int i = start, j = end-1; i < start+n; ++i, --j) { swap(i, j); }
    }

    // ����
    public boolean next() {
        int i = srcLen-2, ii = srcLen-1;
        while (true) {
            while (src[i].compareTo(src[ii]) > 0) { --ii; }
            if (i == ii) {
                i -= 1;
                ii = srcLen-1;
                if (i == -1) {
                    reverse(0, srcLen);
                    return false;
                }
                continue;
            }
            swap(i, ii);
            reverse(i+1, srcLen);
            return true;
        }
    }

    // ����֐�next�̃��t�@�N�^�����O��
    public boolean next_b() {
        int i = srcLen-2;
        while (true) {
            int j = srcLen;
            while (src[i].compareTo(src[--j]) > 0) { }
            if (i != j) {
                swap(i, j);
                reverse(i+1, srcLen);
                return true;
            }
            if (--i == -1) {    // (i == 0) �Ƃ���Ɩ������[�v
                reverse(0, srcLen);
                return false;
            }
        }
    }

    // std::next_permutation@C++�̎�����̈ڐA�F�ł�����
    public boolean next2() {
        int i = srcLen-1;
        while (true) {
            int ii = i;
            --i;
            if (src[i].compareTo(src[ii]) < 0) {
                int j = srcLen;
                while (src[i].compareTo(src[--j]) >= 0) { }
                swap(i, j);
                reverse(ii, srcLen);
                return true;
            }
            if (i == 0) {
                reverse(0, srcLen);
                return false;
            }
        }
    }

    // src�̐���󋵂ɂ���ẮA���ׂĂ̏��񂪐�������Ȃ�
/*
 *  public ArrayList<T[]> getAllPermutations() {
 *      ArrayList<T[]> result = new ArrayList<>(size());
 *      do {
 *          result.add(Arrays.copyOf(src, srcLen));
 *      } while (next_b());
 *      return result;
 *  }
 */

    // src�̐���󋵂Ɋւ�炸���ׂĂ̏���𐶐�����ɂ͂�����̕����ǂ��B
    public ArrayList<T[]> getAllPermutations() {
        ArrayList<T[]> result = new ArrayList<>(size());
        for (int i = 0; i < size(); ++i) {
            result.add(Arrays.copyOf(src, srcLen));
            next();
//            next_b();
//            next2();
        }
        return result;
    }

    public static void main(String[] args) {
        Permutation<Integer> perm = new Permutation<>(new Integer[]{1, 2, 3});
        System.out.println("3! -> " + perm.size());
        for (Integer[] p : perm.getAllPermutations()) {
            System.out.println(Arrays.toString(p));
        }
        System.out.println();

        perm = new Permutation<>(new Integer[]{ 1, 2, 3, 4 });
        System.out.println("4! -> " + perm.size());
        for (Integer[] p : perm.getAllPermutations()) {
            System.out.println(Arrays.toString(p));
        }
        System.out.println();

        perm = new Permutation<>(new Integer[]{ 1, 2, 3, 4, 5 });
        System.out.println("5! -> " + perm.size());
        for (Integer[] p : perm.getAllPermutations()) {
            System.out.println(Arrays.toString(p));
        }
        System.out.println();

        perm = new Permutation<>(new Integer[]{1, 2, 3});
        do {
            System.out.println(Arrays.toString(perm.getPermutation()));
        } while (perm.next());
        System.out.println();

        perm = new Permutation<>(new Integer[]{ 3, 1, 2 });
        ArrayList<Integer[]> result = new ArrayList<>();
        for (int i = 0; i < perm.size(); ++i) {
            result.add(perm.getPermutation());
            System.out.println(Arrays.toString(perm.getPermutation()));
            perm.next();
        }
        System.out.printf("[perm] result.size() -> %d\n", result.size());
        System.out.println();

        Permutation<Pos> perm2 = new Permutation<>(new Pos[] {
            new Pos(0, -2), new Pos(2, 0), new Pos(0, 2), new Pos(-2, 0)
        });
        ArrayList<Pos[]> result2 = perm2.getAllPermutations();
        assert result2.size() == perm2.size();
        for (Pos[] p : result2) {
            System.out.println(Arrays.toString(p));
        }

        ArrayList<ArrayList<Pos>> al = new ArrayList<>(result2.size());
        for (Pos[] p : result2) {
            al.add(new ArrayList<Pos>(Arrays.asList(p)));
        }
        HashSet<ArrayList<Pos>> hs = new HashSet<>(al);
        System.out.println(hs.size());
    }
}
